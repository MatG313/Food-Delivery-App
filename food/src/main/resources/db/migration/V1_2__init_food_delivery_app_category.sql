CREATE TABLE IF NOT EXISTS category
(
    category_id     SERIAL      NOT NULL,
    name            VARCHAR(32) NOT NULL,
    PRIMARY KEY (category_id),
    UNIQUE (category_id)
);