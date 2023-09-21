CREATE TABLE IF NOT EXISTS "user"
(
    user_id     SERIAL      NOT NULL,
    name        VARCHAR(32) NOT NULL,
    surname     VARCHAR(32) NOT NULL,
    email       VARCHAR(32) NOT NULL,
    password    VARCHAR(256) NOT NULL,
    address_id  INT NOT NULL,
    phone       VARCHAR(32) NOT NULL,
    role        INT NOT NULL,
    PRIMARY KEY (user_id),
    UNIQUE (user_id),
    CONSTRAINT fk_user_address
            FOREIGN KEY (address_id)
                REFERENCES address (address_id)
);