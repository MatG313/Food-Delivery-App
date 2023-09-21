CREATE TABLE IF NOT EXISTS orders
(
    orders_id        SERIAL      NOT NULL,
    status          VARCHAR(32) NOT NULL,
    orders_number    INT         NOT NULL,
    dish_id         INT         NOT NULL,
    user_id         INT         NOT NULL,
    restaurant_id   INT         NOT NULL,
    PRIMARY KEY (orders_id),
    UNIQUE (orders_id),
    CONSTRAINT fk_orders_dish
                FOREIGN KEY (dish_id)
                    REFERENCES dish (dish_id),
    CONSTRAINT fk_orders_user
                FOREIGN KEY (user_id)
                    REFERENCES "user" (user_id),
    CONSTRAINT fk_orders_restaurant
                FOREIGN KEY (restaurant_id)
                    REFERENCES restaurant (restaurant_id)
);