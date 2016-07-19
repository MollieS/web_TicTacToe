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
import static play.mvc.Http.Status.OK;
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
    public void loadsGamePage() {
        setUpGame(1);
        Result result = route(fakeRequest("GET", "/game"));
        assertEquals(OK, result.status());
    }


    private void setUpGame(int gameType) {
        Map form = new HashMap();
        form.put("size", "3");
        Http.RequestBuilder boardRequest = fakeRequest(routes.GameController.newBoard()).bodyForm(form);
        Http.Context.current.set(new Http.Context(boardRequest));

        gameController.newBoard();

        form.put("type", String.valueOf(gameType));
        form.put("name", "Human v Human");
        Http.RequestBuilder gameRequest = fakeRequest(routes.GameController.newGame()).bodyForm(form);

        Http.Context.current.set(new Http.Context(gameRequest));

        gameController.newGame();
    }

}
