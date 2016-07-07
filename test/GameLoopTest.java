import org.junit.Test;
import services.GameLoop;
import services.WebPlayer;
import ttt.Player;
import ttt.game.Board;
import ttt.game.GameEngine;
import ttt.game.Marks;

import static org.junit.Assert.assertEquals;

public class GameLoopTest {

    @Test
    public void startsAGame() {
        Player player = new WebPlayer(Marks.X);
        Player player2 = new WebPlayer(Marks.O);
        Board board = new Board(3);
        GameEngine game = new GameEngine(player, player2, board);
        GameLoop loop = new GameLoop(game);
        loop.start();
    }
}
