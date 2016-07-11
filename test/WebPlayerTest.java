import org.junit.Test;
import services.WebPlayer;
import ttt.game.Marks;

import static org.junit.Assert.assertEquals;

public class WebPlayerTest {

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
