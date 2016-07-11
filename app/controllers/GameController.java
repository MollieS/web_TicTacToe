package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import services.WebPlayer;
import ttt.Player;
import ttt.game.Board;
import ttt.game.GameEngine;
import ttt.game.Marks;
import views.html.board;

import java.util.Map;

public class GameController extends Controller {

    private Board gameBoard;
    private GameEngine game;

    public GameController() {
        this.gameBoard = new Board(3);
        this.game = createGame();
    }

    public Result newGame() {
        createGame();
        return redirect("/game");
    }

    public Result showBoard() {
        return ok(board.render(game.showBoard(), game));
    }

    public Result placeMark() {
        Map<String, String[]> request = request().body().asFormUrlEncoded();
        Integer row = Integer.valueOf(request.get("rowNumber")[0]);
        Integer cell = Integer.valueOf(request.get("cellPosition")[0]);
        int move = getMove(row, cell);
        game.play(move);
        return redirect("/game");
    }

    public GameEngine createGame() {
        Player player1 = new WebPlayer(Marks.X);
        Player player2 = new WebPlayer(Marks.O);
        game = new GameEngine(player1, player2, gameBoard);
        return game;
    }

    public int getMove(int row, int cell) {
        return cell + (row * 3);
    }
}
