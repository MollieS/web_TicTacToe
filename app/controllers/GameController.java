package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import services.GameLoop;
import services.WebInput;
import ttt.game.Board;
import ttt.game.GameConstructor;
import ttt.game.GameEngine;
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

    public Result showBoard() {
        String stringBoard = getBoard();
        List<List<String>> rows = gameLoop.getRows();
        return ok(board.render(rows, stringBoard, game.currentMark().toString(), gametype, gameLoop.isFinished(), gameLoop.getStatus()));
    }

    private String getBoard() {
        setFirstPlayerTurn();
        gameLoop.playMove();
        return gameLoop.getBoard();
    }

    public Result newGame() {
        Map<String, String[]> request = request().body().asFormUrlEncoded();
        Integer type = Integer.valueOf(request.get("type")[0]);
        String gameType = request.get("name")[0];
        GameEngine game = createGame(type, gameType);
        this.gameLoop = new GameLoop(game);
        return redirect("/game");
    }

    public Result placeMark() {
        Map<String, String[]> request = request().body().asFormUrlEncoded();
        Integer row = Integer.valueOf(request.get("rowNumber")[0]);
        Integer cell = Integer.valueOf(request.get("cellPosition")[0]);
        playGame(row, cell);
        return redirect("/game");
    }

    public GameEngine createGame(Integer type, String gameType) {
        GameEngine game = GameConstructor.create(Arrays.asList(type, 1), new WebInput());
        this.game = game;
        this.gametype = gameType;
        return game;
    }

    private void playGame(Integer row, Integer cell) {
        int move = getMove(row, cell);
        gameLoop.setPlayerMove(move);
        gameLoop.playMove();
    }

    private void setFirstPlayerTurn() {
        try {
            gameLoop.setPlayerMove(game.getCurrentPlayer().getLocation(new Board(3)));
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private int getMove(int row, int column) {
        return column + (row * 3);
    }
}
