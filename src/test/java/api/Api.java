package api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Api {
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
    public static Response createCard(String id) {
        return given()
                .contentType("application/json")
                .queryParam("idList", id)
                .queryParam("name","testCardName")
                .queryParam("key", apiKey)
                .queryParam("token", token)
                .when()
                .post("/1/cards/");
    }
    public static Response getCardIdList(String id) {
        return given()
                .contentType("application/json")
                .queryParam("key", apiKey)
                .queryParam("token", token)
                .when()
                .get("/1/boards/{id}/lists", id)
                .then()
                .extract()
                .response();
    }

    public static Response updateCard(String id) {
        return given()
                .contentType("application/json")
                .queryParam("name","updateName")
                .queryParam("desc","DESCRIPTION")
                .queryParam("key", apiKey)
                .queryParam("token", token)
                .when()
                .put("/1/cards/{id}", id)
                .then()
                .extract()
                .response();
    }
    public static Response deleteCard(String id) {
        return given()
                .contentType("application/json")
                .queryParam("key", apiKey)
                .queryParam("token", token)
                .when()
                .delete("/1/cards/{id}", id)
                .then()
                .extract()
                .response();
    }
    public static Response deleteBoard(String id) {
        return given()
                .contentType("application/json")
                .queryParam("key", apiKey)
                .queryParam("token", token)
                .when()
                .delete("/1/boards/{id}", id)
                .then()
                .extract()
                .response();
    }
}
