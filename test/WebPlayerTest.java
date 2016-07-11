import org.junit.Before;
import org.junit.Test;
import services.WebPlayer;
import ttt.game.Marks;

import static org.junit.Assert.assertEquals;

public class WebPlayerTest {

    private WebPlayer player;

    @Before
    public void setUp() {
        this.player = new WebPlayer(Marks.X);
    }

    @Test
    public void hasAMark() {
        WebPlayer player = new WebPlayer(Marks.X);
        assertEquals(Marks.X, player.getMark());
    }

    @Test
    public void knowsItsType() {
        WebPlayer player = new WebPlayer(Marks.O);
        assertEquals(WebPlayer.class, player.playerType());
    }
}
