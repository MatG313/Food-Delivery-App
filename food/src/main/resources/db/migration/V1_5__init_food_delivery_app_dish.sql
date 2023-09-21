CREATE TABLE IF NOT EXISTS dish
(
    dish_id     SERIAL NOT NULL,
    name        VARCHAR(64) NOT NULL,
    price       NUMERIC(19,2) NOT NULL,
    description VARCHAR(32),
    img         VARCHAR(64),
    category_id INT NOT NULL,
    restaurant_id INT NOT NULL,
    PRIMARY KEY (dish_id),
    UNIQUE (dish_id),
    CONSTRAINT fk_dish_category
        FOREIGN KEY (category_id)
            REFERENCES category (category_id),
    CONSTRAINT fk_dish_restaurant
        FOREIGN KEY (restaurant_id)
            REFERENCES restaurant (restaurant_id)
);