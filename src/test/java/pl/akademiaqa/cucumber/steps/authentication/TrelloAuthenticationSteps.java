package pl.akademiaqa.cucumber.steps.authentication;

import io.cucumber.java.en.Given;
import pl.akademiaqa.handlers.trello.TrelloAuthentication;

public class TrelloAuthenticationSteps {

    TrelloAuthentication trelloAuthentication = new TrelloAuthentication();

    @Given("I am authenticated to Trello")
    public void i_am_authenticated_to_trello() {
        System.out.println(trelloAuthentication.getKey());
        System.out.println(trelloAuthentication.getToken());
        // dodac klucz i token do query param
    }
}
