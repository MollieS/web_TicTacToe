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

    public int getLocation(Board board) throws Exception {
        return move;
    }

    public Class playerType() {
        return null;
    }
}
