package players;

import ttt.Player;
import ttt.game.Marks;
import ttt.players.PlayerFactory;

public class WebPlayerFactory extends PlayerFactory {

    public static Player create(String playerType, Marks mark) {
        switch (playerType) {
            case WebPlayerType.HUMAN:
                return new WebPlayer(mark);
            default:
                return PlayerFactory.create(playerType, mark);
        }
    }
}
