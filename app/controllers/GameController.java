package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import services.GameHelper;
import views.html.board;

import java.util.Map;

public class GameController extends Controller {

    private GameHelper gameHelper;

    public GameController() {
        this.gameHelper = new GameHelper();
    }

    public Result showBoard() throws Exception {
        return ok(board.render(gameHelper.getPresenter()));
    }

    public Result newBoard() {
        Map<String, String[]> request = request().body().asFormUrlEncoded();
        Integer size = Integer.valueOf(request.get("size")[0]);
        gameHelper.setBoardSize(size);
        return redirect("/");
    }

    public Result newGame() {
        Map<String, String[]> request = request().body().asFormUrlEncoded();
        Integer type = Integer.valueOf(request.get("type")[0]);
        String gameType = request.get("name")[0];
        gameHelper.createGame(type, gameType);
        return redirect("/game");
    }

    public Result placeMark() {
        Map<String, String[]> request = request().body().asFormUrlEncoded();
        Integer move = Integer.valueOf(request.get("position")[0]);
        gameHelper.playGame(move);
        return redirect("/game");
    }
}
