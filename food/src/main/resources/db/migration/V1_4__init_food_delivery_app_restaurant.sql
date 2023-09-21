CREATE TABLE IF NOT EXISTS restaurant
(
    restaurant_id   SERIAL      NOT NULL,
    name            VARCHAR(32) NOT NULL,
    address_id      INT         NOT NULL,
    user_id        INT         NOT NULL,
    range           INT[],
    PRIMARY KEY (restaurant_id),
    UNIQUE (restaurant_id),
    CONSTRAINT fk_restaurant_address
            FOREIGN KEY (address_id)
                REFERENCES address (address_id),
    CONSTRAINT fk_restaurant_user
            FOREIGN KEY (user_id)
                REFERENCES "user" (user_id)
);