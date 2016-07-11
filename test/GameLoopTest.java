import org.junit.Before;
import org.junit.Test;
import services.GameLoop;
import services.WebPlayer;
import ttt.Player;
import ttt.game.Board;
import ttt.game.GameEngine;
import ttt.game.Marks;
import ttt.players.PerfectPlayer;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        loop.setPlayerMove(0);
        loop.playMove();
        assertEquals("X, , , , , , , , ", loop.getBoard());
    }

    @Test
    public void continuesToUpdateBoard() {
        loop.setPlayerMove(0);
        loop.playMove();
        loop.setPlayerMove(1);
        loop.playMove();
        assertEquals("X,O, , , , , , , ", loop.getBoard());
    }

    @Test
    public void canPlayAPerfectPlayer() {
        Player player1 = new WebPlayer(Marks.X);
        Player player2 = new PerfectPlayer(Marks.O);
        Board board = new Board(3);
        GameEngine game = new GameEngine(player1, player2, board);
        GameLoop gameLoop = new GameLoop(game);
        gameLoop.setPlayerMove(0);
        gameLoop.playMove();
        assertEquals("X, , , ,O, , , , ", gameLoop.getBoard());
    }

    @Test
    public void knowsWhenAGameIsDrawn() throws Exception {
        Player player1 = new PerfectPlayer(Marks.X);
        Player player2 = new PerfectPlayer(Marks.O);
        Board board = new Board(3);
        GameEngine game = new GameEngine(player1, player2, board);
        GameLoop gameLoop = new GameLoop(game);
        gameLoop.setPlayerMove(player1.getLocation(board));
        gameLoop.playMove();
        assertTrue(gameLoop.isFinished());
        assertEquals("It's a draw", gameLoop.getStatus());
    }

    @Test
    public void knowsWhenGameIsWon() {
        loop.setPlayerMove(0);
        loop.playMove();
        loop.setPlayerMove(3);
        loop.playMove();
        loop.setPlayerMove(1);
        loop.playMove();
        loop.setPlayerMove(4);
        loop.playMove();
        loop.setPlayerMove(2);
        loop.playMove();
        assertTrue(loop.isFinished());
        assertEquals("X wins!", loop.getStatus());
    }
}
