package services;

import ttt.game.BoardOption;

import java.util.HashMap;

public class BoardMenuPresenter implements MenuPresenter {


    private final String endpoint;

    public BoardMenuPresenter() {
        this.endpoint = "/new-board";
    }

    public HashMap<String, String> showOptions() {
        HashMap<String, String> options = new HashMap<>();
        for (BoardOption boardOption : BoardOption.values()) {
            options.put(boardOption.key, boardOption.title);
        }
        return options;
    }

    public String getEndpoint() {
        return endpoint;
    }
}
