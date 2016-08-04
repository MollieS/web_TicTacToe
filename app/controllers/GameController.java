package controllers;

import com.google.inject.Inject;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import services.GameHelper;
import services.GameMenuPresenter;
import ttt.game.GameOption;
import views.html.board;
import views.html.index;

import java.util.*;

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
        Integer type = Integer.valueOf(data.get("gameType"));
        String gameType = getGameTitle(type);
        Integer boardSize = Integer.valueOf(type);
        gameHelper = gameMap.get(session("game"));
        gameHelper.createGame(type, gameType, boardSize);
        return redirect("/game");
    }

    private String getGameTitle(int gameType) {
        for (GameOption option : GameOption.values()) {
            if (Objects.equals(option.key, String.valueOf(gameType))) {
                return option.title;
            }
        }
        return null;
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
