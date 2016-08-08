import org.junit.Before;
import org.junit.Test;
import players.WebPlayer;
import presenters.BoardPresenter;
import services.GameHelper;
import ttt.game.GameEngine;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class GameHelperTest {

    private GameHelper gameHelper;

    @Before
    public void setUp() {
        gameHelper = new GameHelper();
    }

    @Test
    public void createsGame() {
        gameHelper.createGame("Human v Human", 3);
        assertEquals(GameEngine.class, gameHelper.getGame().getClass());
    }

    @Test
    public void createsWebPlayers() {
        gameHelper.createGame("Human v Human", 3);
        GameEngine game = gameHelper.getGame();
        assertTrue(game != null);
        assertEquals(game.getCurrentPlayer().getClass(), WebPlayer.class);
    }

    @Test
    public void createsBoardPresenter() {
        gameHelper.createGame("Human v Human", 3);
        assertEquals(gameHelper.getPresenter().getClass(), BoardPresenter.class);
    }

    @Test
    public void playsTheGame() {
        gameHelper.createGame("Human v Human", 3);
        gameHelper.playGame(0);
        assertEquals(gameHelper.getPresenter().showBoard(), Arrays.asList("X", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  "));
    }

    @Test
    public void splitsGameTypeAndReturnsCorrectString() {
        String player = gameHelper.getPlayerType(1, "Human v Human");
        assertEquals("HUMAN", player);
    }
}
