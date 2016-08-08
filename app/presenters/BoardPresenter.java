package presenters;

import ttt.game.Board;
import ttt.game.GameEngine;
import ttt.game.Marks;

import java.util.ArrayList;
import java.util.List;

public class BoardPresenter {

    private Board board;
    private GameEngine game;
    private String gameType;
    private boolean isInteractive;

    public BoardPresenter(Board board, GameEngine game, String gameType) {
        this.isInteractive = gameType.contains("Player");
        this.board = board;
        this.game = game;
        this.gameType = gameType;
    }

    public BoardPresenter(Board board, GameEngine game, String gameType, boolean interactive) {
        this.isInteractive = interactive;
        this.board = board;
        this.game = game;
        this.gameType = gameType;
    }

    public BoardPresenter update(Board board, boolean interactive) {
        return new BoardPresenter(board, game, gameType, interactive);
    }

    public List<String> showBoard() {
        List<String> stringBoard = new ArrayList<>();
        convertBoardToString(stringBoard);
        return stringBoard;
    }

    private void convertBoardToString(List<String> stringBoard) {
        for (int i = 0; i < board.size(); i++) {
            showCorrectCell(stringBoard, i);
        }
    }

    private void showCorrectCell(List<String> stringBoard, int i) {
        if (board.getMarkAt(i).equals(Marks.NULL)) {
            stringBoard.add("  ");
        } else {
            stringBoard.add(board.getMarkAt(i).toString());
        }
    }

    public boolean gameIsOver() {
        return game.isOver();
    }

    public String gameStatus() {
        if (game.isWon()) { return game.winningMark() + " wins!"; }
        if (game.isDraw()) { return "It's a draw!"; }
        return game.getCurrentPlayer().getMark() + "'s turn";
    }

    public String gameType() {
        return gameType;
    }

    public boolean gameIsInteractive() {
        return isInteractive;
    }

    public int rowLength() {
        return board.dimensions();
    }

    public boolean isEndOfRow(int cell) {
        return ((cell + 1) % rowLength() == 0);
    }
}
