import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Result;
import play.test.WithApplication;

import controllers.routes;

import static org.junit.Assert.assertTrue;
import static play.test.Helpers.contentAsString;
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
}
