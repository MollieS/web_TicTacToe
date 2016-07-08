package services;

import ttt.Player;
import ttt.game.Board;
import ttt.game.Marks;

public class WebPlayer implements Player {

    private Marks mark;
    private Integer move;

    public WebPlayer(Marks mark) {
        this.mark = mark;
        this.move = null;
    }

    public Marks getMark() {
        return mark;
    }

    public void setMove(int row, int column) {
        this.move =  column + (row * 3);
    }

    public int getLocation(Board board) throws Exception {
        int turn = move;
        this.move = null;
        return turn;
    }

    public Class playerType() {
        return getClass();
    }
}
