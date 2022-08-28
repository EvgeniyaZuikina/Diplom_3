
import model.*;
import org.apache.commons.lang3.RandomStringUtils;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class UserActivity {
    public static final String DOMAIN = "@gmail.com";

    public Map<String, String> register() {

        String email = RandomStringUtils.randomAlphabetic(12) + DOMAIN;
        String password = RandomStringUtils.randomAlphabetic(12);
        String name = RandomStringUtils.randomAlphabetic(12);

        Map<String, String> inputDataMap = new HashMap<>();
        inputDataMap.put("email", email);
        inputDataMap.put("password", password);
        inputDataMap.put("name", name);

        Registration response = given()
                .spec(BaseSpec.getBaseSpec())
                .and()
                .body(inputDataMap)
                .when()
                .post("auth/register")
                .body()
                .as(Registration.class);

        Map<String, String> responseData = new HashMap<>();
        if (response != null) {
            responseData.put("email", response.getUser().getEmail());
            responseData.put("name", response.getUser().getName());
            responseData.put("password", password);

            Tokens.setAccessToken(response.getAccessToken().substring(7));
            Tokens.setRefreshToken(response.getRefreshToken());
        }
        return responseData;
    }

    public void delete() {
        if (Tokens.getAccessToken() == null) {
            return;
        }
        given()
                .spec(BaseSpec.getBaseSpec())
                .auth().oauth2(Tokens.getAccessToken())
                .when()
                .delete("auth/user")
                .then()
                .statusCode(202);
    }

}