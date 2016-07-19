package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import services.GameHelper;
import ttt.game.BoardOption;
import ttt.game.GameOption;
import views.html.board;
import views.html.index;

import java.util.HashMap;
import java.util.Map;

public class GameController extends Controller {

    private HashMap<String, GameHelper> gameMap = new HashMap<>();
    private GameHelper gameHelper;

    public Result chooseGame() {
        HashMap<String, String> gameOptions = new HashMap<>();
        for (GameOption option : GameOption.values()) {
            gameOptions.put(option.key, option.title);
        }
        return ok(index.render("Tic Tac Toe", gameOptions, "/new-game"));
    }

    public Result chooseBoard() {
        HashMap<String, String> boardOptions = new HashMap<>();
        for (BoardOption option : BoardOption.values()) {
            boardOptions.put(option.key, option.title);
        }
        return ok(index.render("Tic Tac Toe", boardOptions, "/new-board"));
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
