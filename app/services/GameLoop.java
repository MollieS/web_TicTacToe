package services;

import ttt.game.GameEngine;
import ttt.game.Marks;

import java.util.ArrayList;
import java.util.List;


public class GameLoop {


    private String board;
    private GameEngine game;
    private List<List<Marks>> rows;

    public GameLoop(GameEngine game) {
        this.board = " , , , , , , , , ";
        this.game = game;
        this.rows = game.showBoard().getRows();
    }

    public String getBoard() {
        return board;
    }

    public List<List<String>> getRows() {
        List<List<String>> allRows = new ArrayList<>();
        for (List<Marks> currentRow : rows) {
            List<String> row = new ArrayList<>();
            for (Marks cell : currentRow) {
                if (cell == Marks.NULL) {
                    row.add(" ");
                } else {
                    row.add(cell.toString());
                }
            }
            allRows.add(row);
        }
        return allRows;
    }

    public void playMove(int move) {
        game.play(move);
        this.rows = game.showBoard().getRows();
        updateBoard();
    }

    private void updateBoard() {
        String board = "";
        for (int i = 0; i < 9; i++) {
            if (game.showBoard().getMarkAt(i) == Marks.NULL) {
                board += " ";
            } else {
                board += game.showBoard().getMarkAt(i).toString();
            }
            if (i < 8) {
                board += ",";
            }
        }
        this.board = board;
    }


}
