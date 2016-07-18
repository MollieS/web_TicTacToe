package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import services.BoardPresenter;
import services.WebPlayer;
import ttt.Player;
import ttt.game.*;
import ttt.players.PlayerFactory;
import views.html.board;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GameController extends Controller {

    private GameEngine game;
    private GameLoop gameLoop;
    private int boardSize;
    private BoardPresenter boardPresenter;
    private String gameType;
    private Map<String, Board> gameSession = new HashMap<>();

    public Result showBoard() throws Exception {
        createBoardPresenter();
        return ok(board.render(boardPresenter));
    }

    public Result newBoard() {
        Map<String, String[]> request = request().body().asFormUrlEncoded();
        String size = request.get("size")[0];
        setBoardSize(size);
        return redirect("/");
    }

    public Result newGame() {
        Map<String, String[]> request = request().body().asFormUrlEncoded();
        Integer type = Integer.valueOf(request.get("type")[0]);
        gameType = request.get("name")[0];
        game = createGame(type, gameType);
        return redirect("/game");
    }

    public Result placeMark() {
        Map<String, String[]> request = request().body().asFormUrlEncoded();
        Integer move = Integer.valueOf(request.get("position")[0]);
        gameLoop.setNextMove(move);
        gameLoop.playMoves();
        boardPresenter = new BoardPresenter(game.showBoard(), game, gameType);
        return redirect("/game");
    }

    private GameEngine createGame(Integer type, String gameType) {
        Player player = new WebPlayer(Marks.X);
        Player player1 = new WebPlayer(Marks.O);
        PlayerFactory factory = new PlayerFactory(player, player1);
        GameEngine game = GameConstructor.create(Arrays.asList(type, boardSize), factory);
        boardPresenter = new BoardPresenter(game.showBoard(), game, gameType);
        gameLoop = new GameLoop(game);
        return game;
    }

    private void createBoardPresenter() {
        updateBoard();
        boardPresenter = new BoardPresenter(game.showBoard(), game, gameType);
    }

    private void updateBoard() {
            try {
                gameLoop.setNextMove(game.getPlayerMove(game.showBoard()));
                gameLoop.playMoves();
            } catch (Exception e) {
                e.getMessage();
            }
    }

    private void setBoardSize(String size) {
        if (("3 x 3").equals(size)) {
            boardSize = 3;
        } else {
            boardSize = 4;
        }
    }

}
