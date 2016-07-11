package services;

import ttt.game.GameEngine;
import ttt.game.Marks;

import java.util.ArrayList;
import java.util.List;


public class GameLoop {

    private String board;
    private GameEngine game;
    private List<List<Marks>> rows;
    private Integer nextMove;
    private String status;

    public GameLoop(GameEngine game) {
        this.board = " , , , , , , , , ";
        this.game = game;
        this.rows = game.showBoard().getRows();
        this.nextMove = null;
        this.status = "In progress";
    }

    public void setPlayerMove(Integer move) {
        this.nextMove = move;
    }

    public String getBoard() {
        return board;
    }

    public List<List<String>> getRows() {
        List<List<String>> allRows = new ArrayList<>();
        fillRows(allRows);
        return allRows;
    }

    public void playMove() {
        while (nextMove != null && !game.isOver()) {
            game.play(nextMove);
            getNextPlayerMove();
            updateBoard();
        }
    }

    private void getNextPlayerMove() {
        nextMove = null;
        if (!game.isOver()) {
            try {
                nextMove = game.getCurrentPlayer().getLocation(game.showBoard());
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }

    private void updateBoard() {
        String board = "";
        board = convertBoardToString(board);
        this.board = board;
        this.rows = game.showBoard().getRows();
    }

    private String convertBoardToString(String board) {
        for (int i = 0; i < 9; i++) {
            Marks cell = game.showBoard().getMarkAt(i);
            board = getStringFromMark(board, cell);
            board = addSplit(board, i);
        }
        return board;
    }

    private String addSplit(String board, int i) {
        if (i < 8) {
            board += ",";
        }
        return board;
    }

    private String getStringFromMark(String board, Marks cell) {
        if (cell == Marks.NULL) {
            board += " ";
        } else {
            board += cell.toString();
        }
        return board;
    }

    private void fillRows(List<List<String>> allRows) {
        for (List<Marks> currentRow : rows) {
            List<String> row = new ArrayList<>();
            for (Marks cell : currentRow) {
                addCellContents(row, cell);
            }
            allRows.add(row);
        }
    }

    private void addCellContents(List<String> row, Marks cell) {
        if (cell == Marks.NULL) {
            row.add(" ");
        } else {
            row.add(cell.toString());
        }
    }

    public boolean isFinished() {
        return game.isOver();
    }

    public String getStatus() {
        if (game.isDraw()) {
            status = "It's a draw";
        } else if (game.isWon()) {
            status = game.winningMark().toString() + " wins!";
        }
        return status;
    }
}
