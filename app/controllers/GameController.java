package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import services.GameLoop;
import services.WebInput;
import ttt.game.GameConstructor;
import ttt.game.GameEngine;
import ttt.players.HumanPlayer;
import views.html.board;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GameController extends Controller {

    private GameEngine game;
    private String gametype;
    private GameLoop gameLoop;

    public GameController() {
        this.gametype = null;
    }

    public GameEngine createGame() {
        Map<String, String[]> request = request().body().asFormUrlEncoded();
        Integer type = Integer.valueOf(request.get("type")[0]);
        String gameType = request.get("name")[0];
        GameEngine game = GameConstructor.create(Arrays.asList(type, 1), new WebInput());
        this.game = game;
        this.gametype = gameType;
        return game;
    }

    public Result showBoard() {
        getComputerMove();
        String stringBoard = gameLoop.getBoard();
        List<List<String>> rows = gameLoop.getRows();
        return ok(board.render(rows, stringBoard, game.currentMark().toString(), gametype, true, "X wins!"));
    }

    public Result newGame() {
        GameEngine game = createGame();
        this.gameLoop = new GameLoop(game);
        Map<String, String[]> request = request().body().asFormUrlEncoded();
        return redirect("/game");
    }

    public Result placeMark() {
        Map<String, String[]> request = request().body().asFormUrlEncoded();
        Integer row = Integer.valueOf(request.get("rowNumber")[0]);
        Integer cell = Integer.valueOf(request.get("cellPosition")[0]);
        int move = getMove(row, cell);
        gameLoop.playMove(move);
        getComputerMove();
        return redirect("/game");
    }


    private void getComputerMove() {
        if (game.getCurrentPlayer().playerType() != HumanPlayer.class) {
            try {
                game.play(game.getCurrentPlayer().getLocation(game.showBoard()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int getMove(int row, int cell) {
        return cell + (row * 3);
    }
}
