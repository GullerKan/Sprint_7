import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

public class TestGetOrders {
    private ValidatableResponse response;
    private OrderRequest orders;
    @Before
    public void setUp() {
        orders = new OrderRequest();
    }

    @Test
    @DisplayName("Получение списка заказов")
    public void checkGetOrders() {
       response = orders.getOrders();
       response.statusCode(200)
               .and().assertThat().body(notNullValue());
    }

}
