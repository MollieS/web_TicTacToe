package services;

import players.WebPlayerFactory;
import presenters.BoardPresenter;
import ttt.Player;
import ttt.game.Board;
import ttt.game.GameEngine;
import ttt.game.GameLoop;
import ttt.game.Marks;

public class GameHelper {

    private BoardPresenter presenter;
    private GameLoop gameLoop;
    private GameEngine game;

    public BoardPresenter getPresenter() {
        createBoardPresenter();
        return presenter;
    }

    public void createGame(String gameType, Integer boardSize) {
        Player player1 = WebPlayerFactory.create(getPlayerType(1, gameType), Marks.X);
        Player player2 = WebPlayerFactory.create(getPlayerType(2, gameType), Marks.O);
        Board board = new Board(boardSize);
        game = new GameEngine(player1, player2, board);
        presenter = new BoardPresenter(game.showBoard(), game, gameType);
        gameLoop = new GameLoop(game);
    }

    public String getPlayerType(int playerNumber, String gameType) {
        String[] words = gameType.split(" ");
        if (playerNumber == 1) {
            return words[0].toUpperCase();
        } else {
            return words[2].toUpperCase();
        }
    }

    public void playGame(Integer move) {
        gameLoop.setNextMove(move);
        gameLoop.playMoves();
        presenter = presenter.update(game.showBoard(), true);
    }

    public GameEngine getGame() {
        return game;
    }

    private void createBoardPresenter() {
        setMoves();
        presenter = presenter.update(game.showBoard(), true);
    }

    private void setMoves() {
        try {
            gameLoop.setNextMove(game.getPlayerMove(game.showBoard()));
            gameLoop.playMoves();
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
