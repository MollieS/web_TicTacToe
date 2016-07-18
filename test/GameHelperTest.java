import org.junit.Before;
import org.junit.Test;
import services.BoardPresenter;
import services.GameHelper;
import services.WebPlayer;
import ttt.game.GameEngine;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;


public class GameHelperTest {

    private GameHelper gameHelper;

    @Before
    public void setUp() {
        gameHelper = new GameHelper();
    }

    @Test
    public void canSetTheBoardSize() {
        gameHelper.setBoardSize(4);
    }

    @Test
    public void createsGame() {
        gameHelper.createGame(1, "Human v Human");
        assertEquals(GameEngine.class, gameHelper.getGame().getClass());
    }

    @Test
    public void createsWebPlayers() {
        gameHelper.createGame(1, "Human v Human");
        GameEngine game = gameHelper.getGame();
        assertEquals(game.getCurrentPlayer().getClass(), WebPlayer.class);
    }

    @Test
    public void createsBoardPresenter() {
        gameHelper.createGame(1, "Human v Human");
        assertEquals(gameHelper.getPresenter().getClass(), BoardPresenter.class);
    }

    @Test
    public void playsTheGame() {
        gameHelper.createGame(1, "Human v Human");
        gameHelper.playGame(0);
        assertEquals(gameHelper.getPresenter().showBoard(), Arrays.asList("X", " ", " ", " ", " ", " ", " ", " ", " "));
    }

}
