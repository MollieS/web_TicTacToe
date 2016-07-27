import controllers.GameController;
import controllers.routes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.fakeRequest;
import static play.test.Helpers.route;

public class GameControllerTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder()
                .configure("play.http.router", "router.Routes")
                .build();
    }

    @After
    public void tearDown() {
        Http.Context.current.remove();
    }

    @Test
    public void loadsBoardChoicePage() {
        Result result = route(fakeRequest("GET", "/"));
        assertEquals(OK, result.status());
    }

    @Test
    public void showsBoardChoices() {
        Result result = route(fakeRequest("GET", "/"));
        assertTrue(contentAsString(result).contains("3x3"));
    }

    @Test
    public void showsGameChoices() {
        Result result = route(fakeRequest("GET", "/"));
        assertTrue(contentAsString(result).contains("Human v Human"));
    }

    @Test
    public void choosingABoardRedirectsBackToMenuPage() {
        Map<String, String> boardChoice = new HashMap<>();
        boardChoice.put("type", "3");
        boardChoice.put("name", "3x3");

        Result boardResult = route(fakeRequest(routes.GameController.newBoard()).bodyForm(boardChoice));

        assertEquals("/", boardResult.header("Location").get());
    }

}

