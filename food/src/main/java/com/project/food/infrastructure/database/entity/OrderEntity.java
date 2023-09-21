package com.project.food.infrastructure.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(of = "orderId")
@ToString(of = {"orderId", "status", "orderNumber"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrderEntity implements Comparable<OrderEntity> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_id")
    private Long orderId;

    @Column(name = "status")
    private String status;

    @Column(name = "orders_number")
    private Integer orderNumber;

    @ManyToOne
    @JoinColumn(name = "restaurantId", referencedColumnName = "restaurant_id")
    private RestaurantEntity restaurantId;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "user_id")
    private UserEntity userId;

    @Column(name = "dish_id")
    private Long dishId;

    @Override
    public int compareTo(OrderEntity o) {
        return this.getOrderNumber().compareTo(o.getOrderNumber());    }
}
