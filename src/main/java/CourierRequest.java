import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CourierRequest extends Client {
    protected final Generator generator = new Generator();
    private static final String COURIER = "/api/v1/courier";
    private static final String LOGIN = "/api/v1/courier/login";
    private static final String DELETE = "api/v1/courier/";
    public ValidatableResponse create(CourierCreateSerialization courier) {
        return given()
                .spec(getSpec())
                .when()
                .body(courier)
                .post(COURIER)
                .then().log().all();
    }
    public ValidatableResponse login( CourierLoginSerialization login) {
        return given()
                .spec(getSpec())
                .when()
                .body(login)
                .post(LOGIN)
                .then().log().all();
    }
    public ValidatableResponse delete(String id) {
        return given()
                .spec(getSpec())
                .when()
                .delete(DELETE + id)
                .then();
    }
}
