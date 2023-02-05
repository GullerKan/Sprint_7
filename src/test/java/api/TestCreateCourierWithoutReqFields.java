package api;

import api.client.CourierRequest;
import api.model.CourierCreateSerialization;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(Parameterized.class)
public class TestCreateCourierWithoutReqFields {
    private String login;
    private String password;
    private String firstName;
    private int status;
    private String id;
    private CourierCreateSerialization data;
    private ValidatableResponse loginResponse;
    private CourierRequest courier;

    public TestCreateCourierWithoutReqFields (String login, String password, String firstName, int status){
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.status = status;
    }

    @Before
    public void setUp() {
        courier = new CourierRequest();
    }

    @After
    public void tearDown() {
    }
    @Parameterized.Parameters
    public  static Object [][] data () {
        return new Object[][] {
                {"login", "", "Petrov", 400},
                {"", "233455", "Petrov", 400},
                {"login", null, "Petrov", 400},
                {null, "233455", "Petrov", 400},
        };
    }

    @Test
    @DisplayName("Проверка обязательности полей login, password")
    public void createCourierWhithoutReqFields(){
        data = new CourierCreateSerialization(login,password,firstName);
        ValidatableResponse response = courier.create(data);
        response.statusCode(status)
                .and().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .and().assertThat().body("code", equalTo(status));
    }
}
