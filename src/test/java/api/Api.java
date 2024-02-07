package apirequests;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiRequests {
    private static final String apiKey = "7f118fdc38e041d4023c64109fa94bef";
    private static final String token = "ATTA5b955fd7c66f0b91071fde50f4b783f40705018766c30a2f82cb1d895f00c50f005A7F21";
    public static Response createBoard(String boardName) {
        return given()
                .contentType("application/json")
                .queryParam("name", boardName)
                .queryParam("key", apiKey)
                .queryParam("token", token)
                .when()
                .post("/1/boards/");
    }
    public String createCard(String boardId) {
        Response response = RestAssured.given()
                .contentType("application/json")
                .queryParam("idList", boardId)
                .queryParam("key", apiKey)
                .queryParam("token", token)
                .when()
                .post("/1/cards/")
                .then()
                .extract()
                .response();
        assertEquals(200, response.getStatusCode(), "createBoard Servisi Hata Aldı");
        String jsonResponse = response.getBody().asString();
        Gson gson = new Gson();
        Map<String, Object> jsonMap = gson.fromJson(jsonResponse, Map.class);
        return (String) jsonMap.get("id");
    }
}
