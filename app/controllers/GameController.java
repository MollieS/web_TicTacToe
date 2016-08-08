package controllers;

import com.google.inject.Inject;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import services.GameHelper;
import presenters.GameMenuPresenter;
import views.html.board;
import views.html.index;

import java.util.HashMap;
import java.util.Map;

public class GameController extends Controller {

    private final FormFactory formFactory;
    private HashMap<String, GameHelper> gameMap = new HashMap<>();
    private GameHelper gameHelper;

    @Inject
    public GameController(FormFactory formFactory) {
        this.formFactory = formFactory;
    }

    public Result showMenus() {
        return ok(index.render("Tic Tac Toe", new GameMenuPresenter()));
    }

    public Result showBoard() throws Exception {
        GameHelper gameHelper = gameMap.get(session("game"));
        return ok(board.render(gameHelper.getPresenter()));
    }

    public Result newGame() {
        createNewSession();
        DynamicForm data = formFactory.form().bindFromRequest();
        String gameType = data.get("gameType");
        Integer boardSize = Integer.valueOf(data.get("boardSize"));
        gameHelper = gameMap.get(session("game"));
        gameHelper.createGame(gameType, boardSize);
        return redirect("/game");
    }

    public Result placeMark() {
        Integer move = Integer.valueOf(getParameter("position"));
        GameHelper gameHelper = gameMap.get(session("game"));
        gameHelper.playGame(move);
        return redirect("/game");
    }

    private void createNewSession() {
        gameHelper = new GameHelper();
        gameMap.put(String.valueOf(gameHelper.hashCode()), gameHelper);
        session("game", String.valueOf(gameHelper.hashCode()));
    }

    private String getParameter(String parameter) {
        Map<String, String[]> request = request().body().asFormUrlEncoded();
        return request.get(parameter)[0];
    }
}
