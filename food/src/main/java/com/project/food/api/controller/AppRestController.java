package com.project.food.api.controller;

import com.project.food.business.service.DishService;
import com.project.food.business.service.OrderService;
import com.project.food.business.service.RestaurantService;
import com.project.food.business.service.UserService;
import com.project.food.domain.User;
import com.project.food.infrastructure.database.entity.DishEntity;
import com.project.food.infrastructure.database.entity.OrderEntity;
import com.project.food.infrastructure.database.entity.RestaurantEntity;
import com.project.food.infrastructure.database.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AppRestController {
    @Autowired
    private UserService userService;
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private DishService dishService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/restaurants")
    ResponseEntity<List<RestaurantEntity>> all() {

        return new ResponseEntity<>(restaurantService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}")
    ResponseEntity<RestaurantEntity> findOne(@PathVariable Long id) {
        RestaurantEntity entity = restaurantService.findById(id);

        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @GetMapping("/dishes")
    ResponseEntity<List<DishEntity>> allDishes() {

        return new ResponseEntity<>(dishService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/dish/{id}")
    ResponseEntity<DishEntity> findDishById(@PathVariable Long id) {
        DishEntity entity = dishService.findById(id);
        System.out.println(entity);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @GetMapping("/orders")
    ResponseEntity<List<OrderEntity>> allOrders() {

        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/order/{id}")
    ResponseEntity<OrderEntity> findOrderById(@PathVariable Long id) {
        OrderEntity entity = orderService.findById(id);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @GetMapping("/users")
    ResponseEntity<List<User>> allUsers() {

        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    ResponseEntity<UserEntity> findOne(@PathVariable Integer id) {
        try {
            UserEntity entity = userService.findUserById(id);
            return new ResponseEntity<>(entity, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
