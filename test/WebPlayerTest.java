import org.junit.Before;
import org.junit.Test;
import services.WebPlayer;
import ttt.game.Board;
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

    @Test
    public void calculatesCorrectMove() throws Exception {
        Board board = new Board(3);
        player.setMove(0, 3);
        assertEquals(3, player.getLocation(board));
        player.setMove(1, 1);
        assertEquals(4, player.getLocation(board));
        player.setMove(2, 1);
        assertEquals(7, player.getLocation(board));
    }
}
