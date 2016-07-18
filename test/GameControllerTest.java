import controllers.routes;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Result;
import play.test.WithApplication;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.OK;
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


    private void setUpGame(int gameType) {
        Map form = new HashMap();
        form.put("size", "3");
        route(fakeRequest(routes.GameController.newBoard()).bodyForm(form));
        form.put("type", String.valueOf(gameType));
        form.put("name", "Human v Human");
        route(fakeRequest(routes.GameController.newGame()).bodyForm(form));
    }

}
