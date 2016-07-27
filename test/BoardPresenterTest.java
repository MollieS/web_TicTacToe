import org.junit.Test;
import services.BoardPresenter;
import services.WebPlayer;
import ttt.Player;
import ttt.game.Board;
import ttt.game.GameEngine;
import ttt.game.Marks;
import ttt.players.PerfectPlayer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BoardPresenterTest {

    @Test
    public void showsEmptyBoard() {
        BoardPresenter boardPresenter = createPresenter(3, "Human");
        assertEquals(9, boardPresenter.showBoard().size());
    }

    @Test
    public void showsBoardWithMark() {
        Board board = new Board(3);
        board = board.placeMark(Marks.X, 0);
        BoardPresenter boardPresenter = new BoardPresenter(board, createGame(board), "Human");
        assertEquals("X", boardPresenter.showBoard().get(0));
    }

    @Test
    public void canDisplayABigBoard() {
        BoardPresenter boardPresenter = createPresenter(4, "Human");
        assertEquals(16, boardPresenter.showBoard().size());
    }

    @Test
    public void knowsTheStatusOfTheGame() {
        BoardPresenter boardPresenter = createPresenter(3, "Human");
        Board board = new Board(4);
        Player player = new WebPlayer(Marks.X);
        Player player2 = new WebPlayer(Marks.O);
        GameEngine game = new GameEngine(player, player2, board);
        assertEquals("X's turn", boardPresenter.gameStatus());
    }

    @Test
    public void knowsWhosTurnItIs() {
        Board board = new Board(4);
        Player player = new WebPlayer(Marks.X);
        Player player2 = new WebPlayer(Marks.O);
        GameEngine game = new GameEngine(player, player2, board);
        game.play(0);
        BoardPresenter boardPresenter = new BoardPresenter(new Board(3), game, "Human");
        assertEquals("O's turn", boardPresenter.gameStatus());
    }

    @Test
    public void knowsTheWinner() {
        GameEngine game = createGame(new Board(3));
        game.play(0);
        game.play(4);
        game.play(1);
        game.play(5);
        game.play(2);
        BoardPresenter boardPresenter = new BoardPresenter(new Board(3), game, "Human");
        assertEquals("X wins!", boardPresenter.gameStatus());
    }

    @Test
    public void displaysADraw() {
        GameEngine game = createGame(new Board(3));
        game.play(0);
        game.play(4);
        game.play(1);
        game.play(2);
        game.play(6);
        game.play(3);
        game.play(5);
        game.play(8);
        game.play(7);
        BoardPresenter boardPresenter = new BoardPresenter(new Board(3), game, "Human");
        assertEquals("It's a draw!", boardPresenter.gameStatus());
    }

    @Test
    public void knowsTheGameType() {
        BoardPresenter boardPresenter = createPresenter(3, "Human v Human");
        assertEquals("Human v Human", boardPresenter.gameType());
    }

    @Test
    public void knowsIfGameIsNonInteractive() {
        BoardPresenter boardPresenter = createPresenter(3, "Human v Human");
        assertFalse(boardPresenter.gameIsInteractive());
    }

    @Test
    public void knowsIfGameIsInteractive() {
        BoardPresenter boardPresenter = createPresenter(3, "Human v Perfect Player");
        assertTrue(boardPresenter.gameIsInteractive());
    }

    @Test
    public void knowsTheLengthOfEachRow() {
        BoardPresenter boardPresenter = createPresenter(3, "Human v Human");
        assertEquals(3, boardPresenter.rowLength());
    }

    @Test
    public void knowsTheLenghtOfEachRowOnASmallBoard() {
        BoardPresenter boardPresenter = createPresenter(4, "Human v Human");
        assertEquals(4, boardPresenter.rowLength());
    }

    @Test
    public void knowsIfACellIsTheEndOfARow() {
        BoardPresenter boardPresenter = createPresenter(3, "Human v Human");
        assertTrue(boardPresenter.isEndOfRow(2));
        assertFalse(boardPresenter.isEndOfRow(3));
        assertTrue(boardPresenter.isEndOfRow(5));
        assertTrue(boardPresenter.isEndOfRow(8));
    }


    private BoardPresenter createPresenter(int boardSize, String gameType) {
        Board board = new Board(boardSize);
        if (gameType.contains("Human")) {
            return new BoardPresenter(board, createGame(board), gameType);
        } else {
            return new BoardPresenter(board, createComputerVComputerGame(board), gameType);
        }
    }

    private GameEngine createGame(Board board) {
        Player player = new WebPlayer(Marks.X);
        Player player2 = new WebPlayer(Marks.O);
        return new GameEngine(player, player2, board);
    }

    private GameEngine createComputerVComputerGame(Board board) {
        Player player = new PerfectPlayer(Marks.X);
        Player player2 = new PerfectPlayer(Marks.O);
        return new GameEngine(player, player2, board);
    }
}
