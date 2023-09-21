package com.project.food.api.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


class AppRestControllerTest {

    @BeforeClass
    public void setUp() {

        RestAssured.baseURI = "http://localhost:8080/api/v1";
    }

    @Test
    public void testGetAllRestaurants() {

        given()
                .when()
                .get("/restaurants")
                .then()
                .statusCode(200)
                .contentType(ContentType.HTML);
    }

    @Test
    public void testGetRestaurantById() {

        given()
                .when()
                .pathParam("id", 1)
                .get("/restaurant/{id}")
                .then()
                .statusCode(200)
                .contentType(ContentType.HTML);


    }

    @Test
    public void testGetAllDishes() {
        given()
                .when()
                .get("/dishes")
                .then()
                .statusCode(200)
                .contentType(ContentType.HTML);
    }

    @Test
    public void testGetDishById() {
        given()
                .when()
                .pathParam("id", 1)
                .get("/dish/{id}")
                .then()
                .statusCode(200)
                .contentType(ContentType.HTML);
    }

    @Test
    public void testGetAllOrders() {
        given()
                .when()
                .get("/orders")
                .then()
                .statusCode(200)
                .contentType(ContentType.HTML);
    }

    @Test
    public void testGetOrderById() {
        given()
                .when()
                .pathParam("id", 1)
                .get("/order/{id}")
                .then()
                .statusCode(200)
                .contentType(ContentType.HTML);
    }

    @Test
    public void testGetAllUsers() {
        given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .contentType(ContentType.HTML);
    }

    @Test
    public void testGetUserById() {
        given()
                .when()
                .pathParam("id", 1)
                .get("/user/{id}")
                .then()
                .statusCode(200)
                .contentType(ContentType.HTML);
    }

}