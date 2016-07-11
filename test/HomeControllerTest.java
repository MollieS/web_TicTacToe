import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Result;
import play.test.WithApplication;

import controllers.routes;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.fakeRequest;
import static play.test.Helpers.route;

public class HomeControllerTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder()
                .configure("play.http.router", "router.Routes")
                .build();
    }

    @Test
    public void canChooseHumanVHumanGame() {
        Result result = route(routes.HomeController.index());
        assertTrue(contentAsString(result).contains("Human v Human"));
    }

    @Test
    public void canChoosePerfectPlayerVHumanGame() {
        Result result = route(routes.HomeController.index());
        assertTrue(contentAsString(result).contains("Perfect Player v Human"));
    }

    @Test
    public void canChooseHumanVPerfectPlayer() {
        Result result = route(routes.HomeController.index());
        assertTrue(contentAsString(result).contains("Human v Perfect Player"));
    }

    @Test
    public void canChoosePerfectPlayerVPerfectPlayer() {
        Result result = route(routes.HomeController.index());
        assertTrue(contentAsString(result).contains("Perfect Player v Perfect Player"));
    }

    @Test
    public void canChooseHumanPlayerVRandomPlayer() {
        Result result = route(routes.HomeController.index());
        assertTrue(contentAsString(result).contains("Human v Random Player"));
    }

    @Test
    public void canChooseRandomPlayerVHumanGame() {
        Result result = route(routes.HomeController.index());
        assertTrue(contentAsString(result).contains("Random Player v Human"));
    }

    @Test
    public void canChooseRandomPlayerVRandomPlayer() {
        Result result = route(routes.HomeController.index());
        assertTrue(contentAsString(result).contains("Random Player v Random Player"));
    }

    @Test
    public void canChooseRandomPlayerVPerfectPlayer() {
        Result result = route(routes.HomeController.index());
        assertTrue(contentAsString(result).contains("Random Player v Perfect Player"));
    }

    @Test
    public void canChoosePerfectPlayerVRandomPlayer() {
        Result result = route(routes.HomeController.index());
        assertTrue(contentAsString(result).contains("Perfect Player v Random Player"));
    }

    @Test
    public void canChooseBoardSize() {
        Result result = route(routes.HomeController.index());
        assertTrue(contentAsString(result).contains("3 x 3"));
        assertTrue(contentAsString(result).contains("4 x 4"));
    }
}
