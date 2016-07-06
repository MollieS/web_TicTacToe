import controllers.GameController;
import controllers.routes;
import org.junit.Before;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Result;
import play.test.WithApplication;
import ttt.game.GameEngine;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.fakeRequest;
import static play.test.Helpers.route;

public class GameControllerTest extends WithApplication {

    private GameController controller;

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder()
                .configure("play.http.router", "router.Routes")
                .build();
    }

    @Before
    public void setUp() {
        this.controller = new GameController();
    }

    @Test
    public void gamePage() {
        Result result = route(fakeRequest("GET", "/"));
        assertEquals(OK, result.status());
    }

    @Test
    public void gamePageShowsBoard() {
        Result result = route(routes.GameController.showBoard());
        assertTrue(contentAsString(result).contains("1"));
    }

    @Test
    public void canAddAMarkToBoard() {
        Map<String, String> form = new HashMap<String, String>();
        form.put("rowNumber", "0");
        form.put("cellPosition", "0");
        route(fakeRequest(routes.GameController.placeMark()).bodyForm(form));
        Result result = route(routes.GameController.showBoard());
        assertTrue(contentAsString(result).contains("X"));
    }

    @Test
    public void calculatesMove() {
        assertEquals(3, controller.getMove(0, 3));
        assertEquals(4, controller.getMove(1, 1));
        assertEquals(7, controller.getMove(2, 1));
    }

    @Test
    public void createsANewGame() {
        Map form = new HashMap();
        form.put("newGame", "0");
        route(fakeRequest(routes.GameController.newGame()).bodyForm(form));
        assertEquals(GameEngine.class, controller.createGame().getClass());
    }

    @Test
    public void canPlayAGame() {
        Map<String, String> form = new HashMap<>();
        form.put("rowNumber", "0");
        form.put("cellPosition", "0");
        route(fakeRequest(routes.GameController.placeMark()).bodyForm(form));
        form = new HashMap<>();
        form.put("rowNumber", "1");
        form.put("cellPosition", "0");
        route(fakeRequest(routes.GameController.placeMark()).bodyForm(form));
        form = new HashMap<>();
        form.put("rowNumber", "0");
        form.put("cellPosition", "1");
        route(fakeRequest(routes.GameController.placeMark()).bodyForm(form));
        form = new HashMap<>();
        form.put("rowNumber", "1");
        form.put("cellPosition", "0");
        route(fakeRequest(routes.GameController.placeMark()).bodyForm(form));
        form = new HashMap<>();
        form.put("rowNumber", "0");
        form.put("cellPosition", "2");
        route(fakeRequest(routes.GameController.placeMark()).bodyForm(form));
        Result result = route(routes.GameController.showBoard());
        assertTrue(contentAsString(result).contains("X wins!"));
    }


}
