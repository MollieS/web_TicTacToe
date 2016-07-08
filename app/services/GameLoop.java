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

    public GameLoop(GameEngine game) {
        this.board = " , , , , , , , , ";
        this.game = game;
        this.rows = game.showBoard().getRows();
        this.nextMove = null;
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
        while (nextMove != null) {
            game.play(nextMove);
            nextMove = null;
            try {
                nextMove = game.getCurrentPlayer().getLocation(game.showBoard());
            } catch (Exception e) {
                e.getMessage();
            }
            this.rows = game.showBoard().getRows();
            updateBoard();
        }
    }

    public int getNextMove() {
        return nextMove;
    }

    private void updateBoard() {
        String board = "";
        board = convertBoardToString(board);
        this.board = board;
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
}
