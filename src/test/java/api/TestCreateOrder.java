package api;

import api.client.OrderRequest;
import api.model.Color;
import api.model.OrderCreateSerialization;
import api.util.EnumColor;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class TestCreateOrder {
    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private List<Color> color;
    private int status;
    private String track;

    private OrderCreateSerialization data;
    private ValidatableResponse response;
    private OrderRequest order;

    public TestCreateOrder(String firstName,
                           String lastName,
                           String address,
                           String metroStation,
                           String phone,
                           int rentTime,
                           String deliveryDate,
                           String comment,
                           List<Color> color,
                           int status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
        this.status = status;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {"Тест", "Тестов", "ул. Ленина 5", "Черкизовская",
                        "+78003553535", 2, "2023-01-23", "Привет", List.of(), 201},
                {"Тест", "Тестов", "ул. Ленина 5", "Черкизовская",
                        "+78003553535", 2, "2023-01-23", "Привет", List.of(EnumColor.GREY), 201},
                {"Тест", "Тестов", "ул. Ленина 5", "Черкизовская",
                        "+78003553535", 2, "2023-01-23", "Привет", List.of(EnumColor.BLACK), 201},
                {"Тест", "Тестов", "ул. Ленина 5", "Черкизовская",
                        "+78003553535", 2, "2023-01-23", "Привет", List.of(EnumColor.GREY, EnumColor.BLACK), 201}
        };
    }
    @After
    public void tearDown(){
        track = response.extract().path("track").toString();
        if (track != null) {
            order.cancelOrders(track);
    }
    }
    @Test
    @DisplayName("Получение списка заказов")
    public void createOrder() {
        order = new OrderRequest();
        data = new OrderCreateSerialization(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        response = order.postOrders(data);
        response.statusCode(status)
                .and().assertThat().body("track", notNullValue());
        }
    }