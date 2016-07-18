import controllers.routes;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
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

    @Test
    public void loadsGamePage() {
        setUpGame(1);
        Result result = route(fakeRequest("GET", "/game"));
        assertEquals(OK, result.status());
    }

    @Test
    public void gamePageShowsBoard() {
        setUpGame(1);
        Map<String,String> form = new HashMap<>();
        form.put("position", "0");
        route(fakeRequest(routes.GameController.placeMark()).bodyForm(form));
        Result result = route(routes.GameController.showBoard());
        int cells = StringUtils.countMatches(contentAsString(result), "getposition");
        assertTrue(cells == 9);
    }

    @Test
    public void gamePageShowsBigBoard() {
        setUpBigBoard();
        Map<String,String> form = new HashMap<>();
        form.put("position", "0");
        route(fakeRequest(routes.GameController.placeMark()).bodyForm(form));
        Result result = route(routes.GameController.showBoard());
        int cells = StringUtils.countMatches(contentAsString(result), "getposition");
        assertEquals(16, cells);
    }

    @Test
    public void canDisplayMarkOnBoard() {
        setUpGame(1);
        Map<String, String> form = new HashMap<String, String>();
        form.put("position", "0");
        route(fakeRequest(routes.GameController.placeMark()).bodyForm(form));
        Result result = route(routes.GameController.showBoard());
        assertTrue(contentAsString(result).contains("X"));
    }

    private void setUpGame(int gameType) {
        Map form = new HashMap();
        form.put("size", "3 x 3");
        route(fakeRequest(routes.GameController.newBoard()).bodyForm(form));
        form.put("type", String.valueOf(gameType));
        form.put("name", "Human v Human");
        route(fakeRequest(routes.GameController.newGame()).bodyForm(form));
    }

    private void setUpBigBoard() {
        Map form = new HashMap();
        form.put("size", "4 x 4");
        route(fakeRequest(routes.GameController.newBoard()).bodyForm(form));
        form.put("type", "1");
        form.put("name", "Human v Human");
        route(fakeRequest(routes.GameController.newGame()).bodyForm(form));
    }

}
