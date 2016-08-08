import org.junit.Test;
import players.WebPlayer;
import players.WebPlayerFactory;
import players.WebPlayerType;
import ttt.Player;
import ttt.game.Marks;
import ttt.players.PerfectPlayer;
import ttt.players.RandomPlayer;

import static org.junit.Assert.assertEquals;

public class WebPlayerFactoryTest {

    @Test
    public void createsAWebPlayer() {
        Player player = WebPlayerFactory.create(WebPlayerType.HUMAN, Marks.X);
        assertEquals(WebPlayer.class, player.playerType());
        assertEquals(player.getMark(), Marks.X);
    }

    @Test
    public void createsAPerfectPlayer() {
        Player player = WebPlayerFactory.create(WebPlayerType.PERFECT, Marks.X);
        assertEquals(PerfectPlayer.class, player.playerType());
    }

    @Test
    public void createsARandomPlayer() {
        Player player = WebPlayerFactory.create(WebPlayerType.RANDOM, Marks.X);
        assertEquals(RandomPlayer.class, player.playerType());
    }
}
