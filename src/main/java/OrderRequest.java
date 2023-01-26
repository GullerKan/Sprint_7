import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class OrderRequest extends Client {
    protected final Generator generator = new Generator();
    private static final String ORDERS = "/api/v1/orders";
    private static final String CANCEL = "/api/v1/orders/cancel";

    public ValidatableResponse getOrders() {
        return given()
                .spec(getSpec())
                .when()
                .get(ORDERS)
                .then().log().all();
    }
    public ValidatableResponse postOrders(OrderCreateSerialization order) {
        return given()
                .spec(getSpec())
                .when()
                .body(order)
                .post(ORDERS)
                .then().log().all();
    }
    public ValidatableResponse cancelOrders(String order) {
        return given()
                .spec(getSpec())
                .when()
                .body(order)
                .put(CANCEL)
                .then().log().all();
    }

}
