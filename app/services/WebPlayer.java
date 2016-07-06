package services;

import ttt.Player;
import ttt.game.Board;
import ttt.game.Marks;

public class WebPlayer implements Player {

    private Marks mark;

    public WebPlayer(Marks mark) {
        this.mark = mark;
    }

    public Marks getMark() {
        return mark;
    }

    public Integer getLocation(Board board) throws Exception {
        return null;
    }

    public Class playerType() {
        return getClass();
    }
}
