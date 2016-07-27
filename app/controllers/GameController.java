package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import services.BoardMenuPresenter;
import services.GameHelper;
import services.GameMenuPresenter;
import services.MenuPresenter;
import views.html.board;
import views.html.index;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameController extends Controller {

    private HashMap<String, GameHelper> gameMap = new HashMap<>();
    private GameHelper gameHelper;
    private BoardMenuPresenter boardMenu = new BoardMenuPresenter();

    public Result showMenus() {
        List<MenuPresenter> menus = Arrays.asList(boardMenu, new GameMenuPresenter());
        return ok(index.render("Tic Tac Toe", menus));
    }

    public Result showBoard() throws Exception {
        GameHelper gameHelper = gameMap.get(session("game"));
        return ok(board.render(gameHelper.getPresenter()));
    }

    public Result newBoard() {
        createNewSession();
        GameHelper gameHelper = gameMap.get(session("game"));
        gameHelper.setBoardSize(getBoardChoice());
        return redirect("/");
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

    private Integer getBoardChoice() {
        Map<String, String[]> request = request().body().asFormUrlEncoded();
        boardMenu.chooseOption(request.get("name")[0]);
        return Integer.valueOf(request.get("type")[0]);
    }

    private void createNewSession() {
        gameHelper = new GameHelper();
        gameMap.put(String.valueOf(gameHelper.hashCode()), gameHelper);
        session("game", String.valueOf(gameHelper.hashCode()));
    }
}
