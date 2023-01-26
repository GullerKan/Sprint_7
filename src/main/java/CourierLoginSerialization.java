public class CourierLoginSerialization {
    private String login;
    private String password;

    public CourierLoginSerialization(String login, String password){
        this.login = login;
        this.password = password;
    }

    public static CourierLoginSerialization from(CourierCreateSerialization courier) {
        return new CourierLoginSerialization(courier.getLogin(),courier.getPassword());
    }
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
