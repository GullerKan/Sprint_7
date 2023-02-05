package api.client;
import api.model.CourierCreateSerialization;
import api.model.CourierLoginSerialization;
import api.util.Generator;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CourierRequest extends Client {
    protected final Generator generator = new Generator();
    private static final String COURIER = "/api/v1/courier";
    private static final String LOGIN = "/api/v1/courier/login";
    private static final String DELETE = "api/v1/courier/";
    @Step("Создание курьера")
    public ValidatableResponse create(CourierCreateSerialization courier) {
        return given()
                .spec(getSpec())
                .when()
                .body(courier)
                .post(COURIER)
                .then().log().all();
    }
    @Step("Авторизация курьера")
    public ValidatableResponse login( CourierLoginSerialization login) {
        return given()
                .spec(getSpec())
                .when()
                .body(login)
                .post(LOGIN)
                .then().log().all();
    }
    @Step("Удаление курьера")
    public ValidatableResponse delete(String id) {
        return given()
                .spec(getSpec())
                .when()
                .delete(DELETE + id)
                .then();
    }
}
