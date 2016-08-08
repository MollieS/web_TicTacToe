package players;

import ttt.Player;
import ttt.game.Marks;
import ttt.players.PerfectPlayer;
import ttt.players.PlayerFactory;
import ttt.players.RandomLocationGenerator;
import ttt.players.RandomPlayer;

public class WebPlayerFactory extends PlayerFactory {

    public static Player create(String playerType, Marks mark) {
        switch (playerType) {
            case WebPlayerType.HUMAN:
                return new WebPlayer(mark);
            case WebPlayerType.PERFECT:
                return new PerfectPlayer(mark);
            default:
                return new RandomPlayer(new RandomLocationGenerator(), mark);
        }
    }
}
