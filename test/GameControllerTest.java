import controllers.GameController;
import controllers.routes;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.util.StringUtil;
import org.junit.Before;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Result;
import play.test.WithApplication;
import services.WebPlayer;
import ttt.game.Board;
import ttt.game.GameEngine;
import ttt.game.Marks;
import ttt.players.PerfectPlayer;

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
        setUpGame(1);
        Result result = route(fakeRequest("GET", "/game"));
        assertEquals(OK, result.status());
    }

    @Test
    public void gamePageShowsBoard() {
        setUpGame(1);
        String stringBoard = " , , , , , , , , ";
        Map<String,String> form = new HashMap<>();
        form.put("board", stringBoard);
        form.put("rowNumber", "0");
        form.put("cellPosition", "0");
        route(fakeRequest(routes.GameController.placeMark()).bodyForm(form));
        Result result = route(routes.GameController.showBoard());
        int cells = StringUtils.countMatches(contentAsString(result), "th class=\"cell\"");
        assertTrue(cells == 9);
    }

    @Test
    public void canDisplayMarkOnBoard() {
        setUpGame(1);
        Map<String, String> form = new HashMap<String, String>();
        form.put("rowNumber", "0");
        form.put("cellPosition", "0");
        form.put("board", "X, , , , , , , , ");
        route(fakeRequest(routes.GameController.placeMark()).bodyForm(form));
        Result result = route(routes.GameController.showBoard());
        assertTrue(contentAsString(result).contains("<p class=\"mark\"> X"));
    }


    @Test
    public void canPlayAGame() {
        setUpGame(1);
        Map form = new HashMap<>();
        form.put("rowNumber", "0");
        form.put("cellPosition", "2");
        form.put("board", "X,X, ,O,O, , , , ");
        route(fakeRequest(routes.GameController.placeMark()).bodyForm(form));
        Result result = route(routes.GameController.showBoard());
        assertTrue(contentAsString(result).contains("X wins!"));
    }

    @Test
    public void createsAHumanVHumanGame() {
        Map form = new HashMap();
        form.put("type", "1");
        form.put("name", "Human v Human");
        route(fakeRequest(routes.GameController.newGame()).bodyForm(form));
        Result result = route(routes.GameController.showBoard());
        assertTrue(contentAsString(result).contains("Human v Human"));
    }


    @Test
    public void canCreateAHumanvPerfectPlayer() {
        Map form = new HashMap();
        form.put("type", "4");
        form.put("name", "Human v Perfect Player");
        route(fakeRequest(routes.GameController.newGame()).bodyForm(form));
        Result result = route(routes.GameController.showBoard());
        assertTrue(contentAsString(result).contains("Human v Perfect Player"));
    }

    @Test
    public void canCreateAPerfectPlayerVPerfectPlayerGame() {
        Map form = new HashMap();
        form.put("type", "6");
        form.put("name", "Human v Perfect Player");
        route(fakeRequest(routes.GameController.newGame()).bodyForm(form));
        Result result = route(routes.GameController.showBoard());
        assertTrue(contentAsString(result).contains("Human v Perfect Player"));
    }

    @Test
    public void perfectPlayerCanMoveFirst() {
        Map form = new HashMap();
        form.put("type", "5");
        form.put("name", "Perfect Player v Human");
        route(fakeRequest(routes.GameController.newGame()).bodyForm(form));
        Result result = route(routes.GameController.showBoard());
        assertTrue(contentAsString(result).contains("Perfect Player v Human"));
        assertTrue(contentAsString(result).contains("O's turn"));
    }

    private void setUpGame(int gameType) {
        Map form = new HashMap();
        form.put("type", String.valueOf(gameType));
        form.put("name", "Human v Human");
        route(fakeRequest(routes.GameController.newGame()).bodyForm(form));
    }

}
