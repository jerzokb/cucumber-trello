package pl.akademiaqa.cucumber.steps.board;

import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import pl.akademiaqa.api.trello.ReadRequest;
import pl.akademiaqa.common.CommonValues;
import pl.akademiaqa.handlers.api.RequestHandler;
import pl.akademiaqa.handlers.api.ResponseHandler;
import pl.akademiaqa.handlers.shared.Context;
import pl.akademiaqa.url.TrelloUrl;

@RequiredArgsConstructor
public class ReadBoardSteps {

    private final ReadRequest readRequest;
    private final RequestHandler requestHandler;
    private final ResponseHandler responseHandler;
    private final Context context;

    @Then("I can read created board details")
    public void i_can_read_created_board_details() {

        Response response = readBoard(CommonValues.BOARD_NAME);

        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
        Assertions.assertThat(response.getBody().jsonPath().getString("name")).isEqualTo(CommonValues.BOARD_NAME);
    }

    @Then("I should not see this board any more")
    public void i_should_not_see_this_board_any_more() {

        Response response = readBoard(CommonValues.BOARD_NAME);

        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_NOT_FOUND);
    }

    @Then("I can create board details with board name {string}")
    public void i_can_create_board_details_with_board_name(String boardName) {
        Response response = readBoard(boardName);

        Assertions.assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
        Assertions.assertThat(response.getBody().jsonPath().getString("name")).isEqualTo(boardName);
    }

    @Then("I should see an error")
    public void i_should_see_an_error() {
        Assertions.assertThat(responseHandler.getStatusCode()).isNotEqualTo(HttpStatus.SC_OK);

        Assertions.assertThat(responseHandler.getStatusCode()).toString().startsWith("4");
    }

    @Then("I see new board name {string}")
    public void i_see_new_board_name(String boardName) {
        Response response = readBoard(CommonValues.BOARD_NAME);
        Assertions.assertThat(response.getBody().jsonPath().getString("name")).isEqualTo(boardName);
    }

    private Response readBoard(String boardName) {
        String boarId = context.getBoards().get(boardName);

        requestHandler.setEndpoint(TrelloUrl.BOARDS);
        requestHandler.addPathParam("id", boarId);

        return readRequest.read(requestHandler);
    }
}
