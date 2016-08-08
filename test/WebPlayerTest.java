import org.junit.Test;
import players.WebPlayer;
import ttt.game.Marks;

import static org.junit.Assert.assertEquals;

public class WebPlayerTest {

    @Test
    public void hasAMark() {
        WebPlayer player = new WebPlayer(Marks.O);
        Marks playerMark = player.getMark();
        assertEquals(Marks.O, playerMark);
    }

}
