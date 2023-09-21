package com.project.food.api.controller;

import com.project.food.api.dto.DishDTO;
import com.project.food.api.dto.OrderDTO;
import com.project.food.api.dto.RegisterDTO;
import com.project.food.api.dto.RestaurantDTO;
import com.project.food.business.service.DishService;
import com.project.food.business.service.OrderService;
import com.project.food.business.service.RestaurantService;
import com.project.food.business.service.UserService;
import com.project.food.infrastructure.database.entity.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class HelloController {

    @Autowired
    private UserService userService;
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private DishService dishService;

    @Autowired
    private OrderService orderService;


    public HelloController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/test")
    public String test() {
        return "test!";
    }

    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        RegisterDTO user = new RegisterDTO();
        model.addAttribute("user", user);

        return "register";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") RegisterDTO registerDTO,
                               BindingResult result,
                               Model model) {
        UserEntity existingUser = userService.findUserByEmail(registerDTO.getEmail());
        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()) {
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        AddressEntity address = userService.assignAddress(registerDTO);

        if (result.hasErrors()) {
            model.addAttribute("user", registerDTO);
            return "/register";
        }
        userService.saveUser(registerDTO, address);

        return "redirect:/register?success";
    }

    @PostMapping("/register-restaurant/save")
    public String registrationRestaurant(HttpServletRequest request,
                                         @Valid @ModelAttribute("restaurant") RestaurantDTO restaurantDTO,
                                         BindingResult result,
                                         Model model) throws NoSuchFieldException {
        RestaurantEntity existingRestaurant = restaurantService.findByName(restaurantDTO.getName());
        if (existingRestaurant != null && existingRestaurant.getName() != null && !existingRestaurant.getName().isEmpty()) {
            result.rejectValue("name", null,
                    "There is already an restaurant registered with the same name");
        }

        AddressEntity address = restaurantService.assignAddress(restaurantDTO);
        UserEntity user = userService.findUserByEmail(request.getRemoteUser());

        if (result.hasErrors()) {
            model.addAttribute("restaurant", restaurantDTO);
            return "/register-restaurant";
        }
        restaurantService.saveRestaurant(restaurantDTO, address, user);

        return "redirect:/register-restaurant?success";
    }

    @PostMapping("/add-new-dish/save")
    public String addingNewDish(HttpServletRequest request,
                                @Valid @ModelAttribute("dish") DishDTO dishDTO,
                                BindingResult result,
                                Model model) {

        CategoryEntity category = dishService.assignCategory(dishDTO);
        String restaurantId = dishDTO.getRestaurantId().toString();
        RestaurantEntity restaurant = restaurantService.findById(Long.valueOf(dishDTO.getRestaurantId()));
        if (result.hasErrors()) {
            model.addAttribute("dish", dishDTO);
            return "/add-new-dish";
        }
        dishService.saveDish(dishDTO, category, restaurant);

        return "redirect:/add-new-dish?success&restaurantId=" + restaurantId;
    }

    @PostMapping("/order/save")
    public String order(@RequestBody MultiValueMap<String,
            String> formData,
                        HttpServletRequest request) {
        formData.remove("_csrf");
        String restaurantId = formData.get("restaurantId").get(0);
        formData.remove("restaurantId");
        String email = request.getUserPrincipal().getName();

        RestaurantEntity restaurant = restaurantService.findById(Long.valueOf(restaurantId));
        UserEntity user = userService.findUserByEmail(email);

        // Sprawdzenie ostatniego numer zamowienia. SELECT orderNumber FROM orders where status="active"  Jezeli NULL -> 1.
        // Przypisanie nastepnego numeru do zmiennej orderNumber.

        for (Map.Entry<String, List<String>> entry : formData.entrySet()) {
            String dishId = entry.getKey();
            List<String> l = entry.getValue();
            for (String dishQuantity : l) {
                OrderDTO orderDTO = createOrder(dishId, dishQuantity, restaurantId, user.getUserId());
                DishEntity dish = dishService.findById(orderDTO.getDishId());
                orderService.saveOrder(orderDTO, user, restaurant, dish);
            }
        }

        return "redirect:/restaurant-dashboard?id=" + restaurantId;
    }


    @GetMapping("/dashboard")
    public String dashboard(HttpServletRequest request,
                            @Valid @ModelAttribute("restaurant") RestaurantDTO restaurantDTO,
                            BindingResult result,
                            Model model) {

        int pageSize = 100;
        int currentPage = 1;

        if (request.isUserInRole("ROLE_OWNER")) {
            // Get the currently logged-in user by their email
            String email = request.getUserPrincipal().getName();
            UserEntity user = userService.findUserByEmail(email);

            // Find restaurants associated with the user
            List<RestaurantEntity> restaurants = restaurantService.getRestaurantsByUserId(user.getUserId());
            user.setRestaurants(restaurants);

            List<RestaurantEntity> restaurant1 = restaurants.stream()
                    .sorted()
                    .skip((currentPage - 1) * pageSize)
                    .limit(pageSize)
                    .collect(Collectors.toList());
            model.addAttribute("restaurants", restaurant1);

            return "owner";
        } else if (request.isUserInRole("ROLE_USER")) {
            String street = request.getParameter("street");
            List<RestaurantEntity> restaurants = restaurantService.findAll();
            List<RestaurantEntity> restaurants_display = new ArrayList<>();
            if (street != null) {
                for (RestaurantEntity restaurant : restaurants) {
                    if (restaurant.getRange().contains(street)) {
                        restaurants_display.add(restaurant);
                    }
                }
            }
            List<RestaurantEntity> sortedRestaurant = restaurants_display.stream().sorted().toList();
            model.addAttribute("restaurants", sortedRestaurant);

            UserEntity user = userService.findUserByEmail(request.getRemoteUser());
            List<OrderEntity> orders = orderService.getOrdersByUser(user.getUserId());
            List<OrderEntity> sortedOrders = orders.stream()
                    .sorted()
                    .skip((currentPage - 1) * pageSize)
                    .limit(pageSize)
                    .toList();
            model.addAttribute("orders", sortedOrders);


            return "users";
        } else if (request.isUserInRole("ROLE_ADMIN")) {
            System.out.println("admin");
            return "admin";
        } else {
            System.out.println("user2");
            return "users";
        }
    }

    @GetMapping("/restaurant-dashboard")
    public String restaurantDashboard(
            HttpServletRequest request,
            @ModelAttribute("order") OrderDTO orderDTO,
            @RequestParam String id,
            Model model) {
        if (request.isUserInRole("ROLE_OWNER")) {
            model.addAttribute("restaurantID", id);

            List<DishEntity> dishes = dishService.getDishesByRestaurant(Long.valueOf(id));
            List<DishEntity> sortedMenu = dishes.stream().sorted().collect(Collectors.toList());
            model.addAttribute("menu", sortedMenu);

            RestaurantEntity restaurant = restaurantService.findById(Long.valueOf(id));
            List<OrderEntity> orders = orderService.getOrdersByRestaurant(restaurant.getRestaurantId());
            List<OrderEntity> sortedOrders = orders.stream().sorted().toList();
            model.addAttribute("orders", sortedOrders);

            return "restaurant-owner";
        } else if (request.isUserInRole("ROLE_USER")) {
            model.addAttribute("restaurantID", id);

            List<DishEntity> dishes = dishService.getDishesByRestaurant(Long.valueOf(id));
            List<DishEntity> sortedMenu = dishes.stream().sorted().collect(Collectors.toList());
            model.addAttribute("menu", sortedMenu);

            return "restaurant-users";
        } else if (request.isUserInRole("ROLE_ADMIN")) {
            System.out.println("admin");
            return "restaurant-owner";
        } else {
            System.out.println("user2");
            return "restaurant-users";
        }

    }

    @GetMapping("/register-restaurant")
    public String restaurantRegister(Model model) {
        RestaurantDTO restaurant = new RestaurantDTO();
        model.addAttribute("restaurant", restaurant);

        return "register-restaurant";
    }

    @GetMapping("/add-new-dish")
    public String addNewDish(
            @RequestParam String restaurantId,
            Model model) {
        DishDTO dish = new DishDTO();
        RestaurantDTO RestaurantDTO = new RestaurantDTO();
        model.addAttribute("dish", dish);
        model.addAttribute("restaurant", restaurantId);

        return "add-new-dish";
    }

    private OrderDTO createOrder(String orderId, String dishQuantity, String restuarantId, Long userId) {
        OrderDTO orderDto = new OrderDTO();
        orderDto.setStatus("active");
        orderDto.setOrderNumber(Integer.parseInt(dishQuantity));
        orderDto.setRestaurantId(Long.parseLong(restuarantId));
        orderDto.setUserId(userId.intValue());
        orderDto.setDishId(Long.parseLong(orderId.split("-", 2)[1]));
        return orderDto;
    }

    @RequestMapping("/edit-status/{id}")
    public String editStatus(@PathVariable("id") String id, HttpServletRequest request) {
        orderService.toggleStatus(Long.valueOf(id));
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @PostMapping("/edit-status/save")
    public String editStatusSuccessfully(HttpServletRequest request,
                                         @Valid @ModelAttribute("order") OrderEntity orderEntity,
                                         @RequestParam("status") String status,
                                         BindingResult result,
                                         Model model) {
        orderEntity.setStatus(status);

        return "redirect:/edit-status/save?success";
    }

}