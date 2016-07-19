import controllers.HomeController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
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

    private HomeController homeController;

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder()
                .configure("play.http.router", "router.Routes")
                .build();
    }

    @Before
    public void setUp() {
        this.homeController = new HomeController();
    }

    @After
    public void tearDown() {
        Http.Context.current.remove();
    }

    @Test
    public void loadsIndexPage() {
        Result result = homeController.index();
        assertTrue(contentAsString(result).contains("Tic Tac Toe"));
    }
}
