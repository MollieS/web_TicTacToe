package services;

import ttt.Player;
import ttt.game.*;
import ttt.players.PlayerFactory;

import java.util.Arrays;

public class GameHelper {

    private String gameType;
    private Integer boardSize;
    private BoardPresenter presenter;
    private GameLoop gameLoop;
    private GameEngine game;

    public GameHelper() {
        this.boardSize = 3;
    }

    public BoardPresenter getPresenter() {
        createBoardPresenter();
        return presenter;
    }

    public void createGame(Integer type, String gameType) {
        setGameType(gameType);
        game = GameConstructor.create(Arrays.asList(type, boardSize), getPlayerFactory());
        presenter = new BoardPresenter(game.showBoard(), game, gameType);
        gameLoop = new GameLoop(game);
    }

    public void playGame(Integer move) {
        gameLoop.setNextMove(move);
        gameLoop.playMoves();
        presenter = new BoardPresenter(game.showBoard(), game, gameType);
    }

    public GameEngine getGame() {
        return game;
    }


    private void createBoardPresenter() {
        setMoves();
        presenter = new BoardPresenter(game.showBoard(), game, gameType);
    }

    public void setBoardSize(int size) {
        boardSize = size;
    }

    public int getBoardSize() {
        return boardSize;
    }

    private PlayerFactory getPlayerFactory() {
        Player player = new WebPlayer(Marks.X);
        Player player1 = new WebPlayer(Marks.O);
        return new PlayerFactory(player, player1);
    }

    private void setGameType(String gameType) {
        this.gameType = gameType;
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
