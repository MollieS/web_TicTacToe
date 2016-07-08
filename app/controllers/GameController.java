package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import services.WebInput;
import ttt.game.GameConstructor;
import ttt.game.GameEngine;
import ttt.players.HumanPlayer;
import views.html.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GameController extends Controller {

    private GameEngine game;
    private String gametype;
    private List<List<String>> stringBoard;

    public GameController() {
        this.game = null;
        this.gametype = null;
        List<String> row = Arrays.asList(" ", " ", " ");
        this.stringBoard = Arrays.asList(row, row, row);
    }

    public Result showBoard() {
        getComputerMove();
        return ok(board.render(stringBoard, game.currentMark().toString(), gametype, true, "X wins!"));
    }

    public Result newGame() {
        createGame();
        Map<String, String[]> request = request().body().asFormUrlEncoded();
        return redirect("/game");
    }


    public Result placeMark() {
        Map<String, String[]> request = request().body().asFormUrlEncoded();
        this.stringBoard = formatBoard(request.get("board")[0]);
        Integer row = Integer.valueOf(request.get("rowNumber")[0]);
        Integer cell = Integer.valueOf(request.get("cellPosition")[0]);
        int move = getMove(row, cell);
        game.play(move);
        getComputerMove();
        return redirect("/game");
    }

    private List<List<String>> formatBoard(String board) {
        int rowStart = 0;
        String[] boardCells = board.split(",");
        List<List<String>> rows = new ArrayList<>();
        for (int cell = 0; cell < 3; cell++) {
            List<String> cells = new ArrayList<>();
            for (int currentCell = 0; currentCell < 3; currentCell++) {
                cells.add(boardCells[currentCell + rowStart]);
            }
            rows.add(cells);
            rowStart += 3;
        }
        return rows;
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

    public GameEngine createGame() {
        Map<String, String[]> request = request().body().asFormUrlEncoded();
        Integer type = Integer.valueOf(request.get("type")[0]);
        String gameType = request.get("name")[0];
        GameEngine game = GameConstructor.create(Arrays.asList(type, 1), new WebInput());
        this.game = game;
        this.gametype = gameType;
        return game;
    }

    public int getMove(int row, int cell) {
        return cell + (row * 3);
    }
}
