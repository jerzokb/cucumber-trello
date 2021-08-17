package pl.akademiaqa.api.trello.boards;

import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import pl.akademiaqa.handlers.api.RequestHandler;

import static io.restassured.RestAssured.given;

@RequiredArgsConstructor
public class CreateBoardRequest {

    private final BaseRequest baseRequest;

    public Response createBoard(RequestHandler requestHandler) {

        return given()
                .spec(baseRequest.requestsSetup(requestHandler.getQueryParams()))
                .when()
                .post(requestHandler.getEndpoint())
                .then()
                .log().ifError()
                .extract()
                .response();
    }
}
