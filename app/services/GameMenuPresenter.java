package services;

import ttt.game.GameOption;

import java.util.HashMap;

public class GameMenuPresenter implements MenuPresenter {

    private String endpoint;

    public GameMenuPresenter() {
        this.endpoint = "/new-game";
    }

    public HashMap<String, String> showOptions() {
        HashMap<String, String> options = new HashMap<>();
        for (GameOption gameOption : GameOption.values()) {
            options.put(gameOption.key, gameOption.title);
        }
        return options;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
