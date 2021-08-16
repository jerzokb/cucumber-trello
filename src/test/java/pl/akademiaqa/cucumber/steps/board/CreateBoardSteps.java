package pl.akademiaqa.cucumber.steps.board;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateBoardSteps {
    @When("I create new board")
    public void i_create_new_board() {
        // request POST do API
        // response - status code 201
    }
    @Then("I see created board on the list")
    public void i_see_created_board_on_the_list() {
        // request GET na/boards
        // sprawdzenie czy board został dodany do listy
    }
}
