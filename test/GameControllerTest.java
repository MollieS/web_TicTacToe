import controllers.GameController;
import controllers.routes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import play.Application;
import play.api.test.FakeRequest;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.mvc.Http.RequestBuilder;
import play.mvc.Result;
import play.test.WithApplication;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static play.mvc.Http.Status.OK;
import static play.mvc.Http.Status.SEE_OTHER;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.fakeRequest;
import static play.test.Helpers.route;

public class GameControllerTest extends WithApplication {

    private GameController gameController;

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder()
                .configure("play.http.router", "router.Routes")
                .build();
    }

    @Before
    public void setUp() {
        this.gameController = new GameController();
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
    public void choosingABoardRedirectsToGameChoice() {
        Map<String, String> boardChoice = new HashMap<>();
        boardChoice.put("type", "3");

        Result boardResult = route(fakeRequest(routes.GameController.newBoard()).bodyForm(boardChoice));

        assertEquals(SEE_OTHER, boardResult.status());
        assertEquals("/choose-game", boardResult.header("Location").get());
    }

    @Test
    public void loadsChooseGamePage() {
        Result result = route(fakeRequest("GET", "/choose-game"));
        assertEquals(OK, result.status());
    }

    @Test
    public void showsGameOptions() {
        Result result = route(fakeRequest("GET", "/choose-game"));
        assertTrue(contentAsString(result).contains("Human v Human"));
    }

}

