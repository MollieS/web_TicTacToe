package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import services.BoardMenuPresenter;
import services.GameHelper;
import services.GameMenuPresenter;
import views.html.board;
import views.html.index;

import java.util.HashMap;
import java.util.Map;

public class GameController extends Controller {

    private HashMap<String, GameHelper> gameMap = new HashMap<>();
    private GameHelper gameHelper;

    public Result chooseGame() {
        return ok(index.render("Tic Tac Toe", new GameMenuPresenter()));
    }

    public Result chooseBoard() {
        return ok(index.render("Tic Tac Toe", new BoardMenuPresenter()));
    }

    public Result showBoard() throws Exception {
        GameHelper gameHelper = gameMap.get(session("game"));
        return ok(board.render(gameHelper.getPresenter()));
    }

    public Result newBoard() {
        gameHelper = new GameHelper();
        gameMap.put(String.valueOf(gameHelper.hashCode()), gameHelper);
        session("game", String.valueOf(gameHelper.hashCode()));
        Map<String, String[]> request = request().body().asFormUrlEncoded();
        Integer size = Integer.valueOf(request.get("type")[0]);
        GameHelper gameHelper = gameMap.get(session("game"));
        gameHelper.setBoardSize(size);
        return redirect("/choose-game");
    }

    public Result newGame() {
        Map<String, String[]> request = request().body().asFormUrlEncoded();
        Integer type = Integer.valueOf(request.get("type")[0]);
        String gameType = request.get("name")[0];
        gameHelper = gameMap.get(session("game"));
        gameHelper.createGame(type, gameType);
        return redirect("/game");
    }

    public Result placeMark() {
        Map<String, String[]> request = request().body().asFormUrlEncoded();
        Integer move = Integer.valueOf(request.get("position")[0]);
        GameHelper gameHelper = gameMap.get(session("game"));
        gameHelper.playGame(move);
        return redirect("/game");
    }
}
