import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.*;


public class TestLoginCourier {
    private CourierLoginSerialization data;

    private CourierRequest courier;
    @Before
    public void setUp() {
        courier = new CourierRequest();
    }

    @Test
    @DisplayName("Успешный логин курьера")
    @Description("Существующий юзер, переданны обязательные параметры соответствующего типа. 200 - id курьера")
    public void loginCourier() {
        data = new CourierLoginSerialization("noNameUser", "254");
        ValidatableResponse response = courier.login(data);
        response.statusCode(200)
                .and().assertThat().body("id", notNullValue());
    }
    @Test
    @DisplayName("Проверка обязательности полей")
    @Description("Запрос авторизации без логина.  400 - Недостаточно данных для входа")
    public void loginCourierWithoutLogin() {
        data = new CourierLoginSerialization("", "254");
        ValidatableResponse response = courier.login(data);
        response.statusCode(400)
                .and().assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }
    @Test
    @DisplayName("Проверка обязательности полей")
    @Description("Запрос авторизации без пароля. 400 - Недостаточно данных для входа")
    public void loginCourierWithoutPassword() {
        data = new CourierLoginSerialization("noNameUser", "");
        ValidatableResponse response = courier.login(data);
        response.statusCode(400)
                .and().assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }
    @Test
    @DisplayName("Несуществующий логин")
    @Description("Запрос авторизации с несуществующим парой логин-пароль. 404 - Учетная запись не найдена")
    public void loginCourierWithNotExistLogin() {
        data = new CourierLoginSerialization("noNameUser_", "254");
        ValidatableResponse response = courier.login(data);
        response.statusCode(404)
                .and().assertThat().body("message", equalTo("Учетная запись не найдена"));
    }
    @Test
    @DisplayName("Некорректный пароль")
    @Description("Запрос авторизации с несуществующей парой логин-пароль. 404 - Учетная запись не найдена")
    public void loginCourierWithIncorectPassword() {
        data = new CourierLoginSerialization("noNameUser", "25");
        ValidatableResponse response = courier.login(data);
        response.statusCode(404)
                .and().assertThat().body("message", equalTo("Учетная запись не найдена"));
    }
}
