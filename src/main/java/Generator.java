import org.apache.commons.lang3.RandomStringUtils;
public class Generator {
    public static CourierCreateSerialization random(){
        return new CourierCreateSerialization(RandomStringUtils.randomAlphabetic(5),"123123", "Name");
    }
}
