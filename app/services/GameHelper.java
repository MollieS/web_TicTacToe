package services;

import ttt.Player;
import ttt.game.*;
import ttt.players.PlayerFactory;

import java.util.Arrays;

public class GameHelper {

    private BoardPresenter presenter;
    private GameLoop gameLoop;
    private GameEngine game;

    public BoardPresenter getPresenter() {
        createBoardPresenter();
        return presenter;
    }

    public void createGame(Integer type, String gameType, Integer boardSize) {
        game = GameConstructor.create(Arrays.asList(type, boardSize), getPlayerFactory());
        presenter = new BoardPresenter(game.showBoard(), game, gameType);
        gameLoop = new GameLoop(game);
    }

    public void playGame(Integer move) {
        gameLoop.setNextMove(move);
        gameLoop.playMoves();
        presenter = presenter.update(game.showBoard(), gameLoop.hasNextMove());
    }

    public GameEngine getGame() {
        return game;
    }

    private void createBoardPresenter() {
        setMoves();
        presenter = presenter.update(game.showBoard(), gameLoop.hasNextMove());
    }

    private PlayerFactory getPlayerFactory() {
        Player player = new WebPlayer(Marks.X);
        Player player1 = new WebPlayer(Marks.O);
        return new PlayerFactory(player, player1);
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
