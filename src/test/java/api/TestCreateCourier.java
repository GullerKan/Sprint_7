package api;

import api.client.CourierRequest;
import api.model.CourierCreateSerialization;
import api.model.CourierLoginSerialization;
import api.util.Generator;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;

public class TestCreateCourier {
    private CourierCreateSerialization randomCourier;
    private String id;
    private CourierCreateSerialization data;
    private ValidatableResponse loginResponse;
    private ValidatableResponse response;

    private CourierRequest courier;

    @Before
    public void setUp() {
        randomCourier = Generator.random();
        courier = new CourierRequest();

    }
    @After
    public void tearDown() {
        loginResponse = courier.login(CourierLoginSerialization.from(data));
        id = loginResponse.extract().path("id").toString();
       if (id != null) {
            courier.delete(id);
        }
    }

    @Test
    @DisplayName("Cоздание курьера")
    @Description("Проверка создания курьера с уникальным логином")
    public void createNewCourier(){
        data = new CourierCreateSerialization("egeefgddh","347333","Петя");
        ValidatableResponse response = courier.create(data);
        response.statusCode(201)
                .and().assertThat().body("ok", is(true));
    }

    @Test
    @DisplayName("Cоздание курьера под неуникальным логином")
    @Description("Проверка невозможности создания курьера под существующим логином")
    public void createCourierWhithExistingLogin() {
        data = randomCourier;
        for (int i = 0; i < 2; i++) {
            response = courier.create(randomCourier);
        }
        response.statusCode(not(201));
    }

    @Test
    @DisplayName("Cоздание курьера под неуникальным логином")
    @Description("Проверка соответствия response")
    public void createCourierWhithExistingLoginCheckResponse() {
        data = randomCourier;
        for (int i = 0; i < 2; i++) {
            response = courier.create(randomCourier);
        }
        response.statusCode(409)
                .and().assertThat().body("message", equalTo("Этот логин уже используется"));//Ожидаемый message согласно доке (тест падает)
    }
}