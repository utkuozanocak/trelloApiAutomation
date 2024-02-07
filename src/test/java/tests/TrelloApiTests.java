package tests;

import api.Api;
import base.BaseTest;
import baseactions.BaseActions;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrelloApiTests extends BaseTest {
    List<String> cardList = new ArrayList<>();
    @BeforeAll
    public static void setup() {
        BaseTest.setup();
    }
    @Test
    public void trelloTest() {
        Gson gson = new Gson();

        Response createBoardResponse = Api.createBoard("testBoard");
        assertEquals(200, createBoardResponse.getStatusCode(), "createBoard Servisi Hata Aldı");
        String jsonResponseCardList = createBoardResponse.getBody().asString();
        Map<String, Object> jsonMapCardList = gson.fromJson(jsonResponseCardList, Map.class);
        String boardId = jsonMapCardList.get("id").toString();

        Response cardIdListResponse = Api.getCardIdList(boardId);
        assertEquals(200, cardIdListResponse.getStatusCode(), "getCardIdList Servisi Hata Aldı");
        List<Map<String, Object>> jsonArray = JsonPath.from(cardIdListResponse.getBody().asString()).getList("$");

        for (int i=0; i<2; i++) {
            Response createCardResponse = Api.createCard(jsonArray.get(i).get("id").toString());
            assertEquals(200, createCardResponse.getStatusCode(), "createCard Servisi Hata Aldı");
            String jsonResponseCardIdList = createCardResponse.getBody().asString();
            Map<String, Object> jsonMapCardIdList = gson.fromJson(jsonResponseCardIdList, Map.class);
            cardList.add(jsonMapCardIdList.get("id").toString());
        }

        Response updateCardResponse = Api.updateCard(cardList.get(BaseActions.generateRandomNumber(0,cardList.size()-1)));
        assertEquals(200, updateCardResponse.getStatusCode(), "updateCard Servisi Hata Aldı");

        for (String cardId : cardList) {
            Response deleteCardResponse = Api.deleteCard(cardId);
            assertEquals(200, deleteCardResponse.getStatusCode(), "deleteCard Servisi Hata Aldı");
        }

        Response deleteBoardResponse = Api.deleteBoard(boardId);
        assertEquals(200, deleteBoardResponse.getStatusCode(), "deleteBoard Servisi Hata Aldı");
    }
}
