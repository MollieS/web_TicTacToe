package services;

import ttt.game.Board;
import ttt.game.GameEngine;
import ttt.game.Marks;

import java.util.ArrayList;
import java.util.List;

public class BoardPresenter {

    private Board board;
    private GameEngine game;
    private String gameType;

    public BoardPresenter(Board board, GameEngine game, String gameType) {
        this.board = board;
        this.game = game;
        this.gameType = gameType;
    }

    public List<String> showBoard() {
        List<String> stringBoard = new ArrayList<>();
        for (int i = 0; i < board.size(); i++) {
            if (board.getMarkAt(i).equals(Marks.NULL)) {
                stringBoard.add(" ");
            } else {
                stringBoard.add(board.getMarkAt(i).toString());
            }
        }
        return stringBoard;
    }

    public String gameStatus() {
        if (game.isWon()) {
            return game.winningMark() + " wins!";
        } else if (game.isDraw()) {
            return "It's a draw!";
        }
        return game.currentMark() + "'s turn";
    }

    public String gameType() {
        return gameType;
    }

    public boolean gameIsInteractive() {
        if (gameType.contains("Human")) {
            return true;
        }
        return false;
    }
}
