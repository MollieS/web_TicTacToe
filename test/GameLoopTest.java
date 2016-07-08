import org.junit.Before;
import org.junit.Test;
import services.GameLoop;
import services.WebPlayer;
import ttt.Player;
import ttt.game.Board;
import ttt.game.GameEngine;
import ttt.game.Marks;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GameLoopTest {

    private GameLoop loop;

    @Before
    public void setUp() {
        Board board = new Board(3);
        Player player1 = new WebPlayer(Marks.X);
        Player player2 = new WebPlayer(Marks.O);
        GameEngine game = new GameEngine(player1, player2, board);
        this.loop = new GameLoop(game);
    }

    @Test
    public void hasAnEmptyStringBoard() {
        assertEquals(" , , , , , , , , ", loop.getBoard());
    }

    @Test
    public void canReturnTheRows() {
        List<String> row = Arrays.asList(" ", " ", " ");
        List<List<String>> allRows = Arrays.asList(row, row, row);
        assertEquals(allRows, loop.getRows());
    }

    @Test
    public void updatesBoardWhenMarkIsPlaced() {
        loop.playMove(0);
        assertEquals("X, , , , , , , , ", loop.getBoard());
    }

    @Test
    public void continuesToUpdateBoard() {
        loop.playMove(0);
        loop.playMove(1);
        assertEquals("X,O, , , , , , , ", loop.getBoard());
    }
}
