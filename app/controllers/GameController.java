package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import ttt.game.Board;
import ttt.game.Marks;
import views.html.board;

public class GameController extends Controller {

    private Board gameBoard;

    public GameController() {
        this.gameBoard = new Board(3);
    }

    public Result showBoard() {
        return ok(board.render(gameBoard.getRows()));
    }

    public Result placeMark() {
        this.gameBoard = gameBoard.placeMark(Marks.X, 0);
        return redirect("/game");
    }
}
