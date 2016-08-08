package presenters;

import ttt.game.GameOption;

import java.util.ArrayList;
import java.util.List;

public class GameMenuPresenter {

    public List<String> showOptions() {
        List<String> options = new ArrayList<>();
        for (GameOption gameOption : GameOption.values()) {
            options.add(gameOption.title);
        }
        return options;
    }
}
