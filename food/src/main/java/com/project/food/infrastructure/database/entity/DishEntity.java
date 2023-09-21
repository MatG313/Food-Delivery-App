package com.project.food.infrastructure.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(of = "dishId")
@ToString(of = {"dishId", "name", "price", "description"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dish")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DishEntity implements Comparable<DishEntity>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dish_id")
    private Long dishId;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "description")
    private String description;

    @Column(name = "img")
    private String img;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", referencedColumnName = "restaurant_id")
    @JsonManagedReference
    private RestaurantEntity restaurantId;

    @ManyToOne
    @JoinColumn(name = "categoryId", referencedColumnName = "category_id")
    private CategoryEntity category_id;

    @ManyToOne
    @JoinColumn(name = "orderId", referencedColumnName = "orders_id")
    private OrderEntity orderId;

    @Override
    public int compareTo(DishEntity o) {
        return this.getName().compareTo(o.getName());
    }
}
