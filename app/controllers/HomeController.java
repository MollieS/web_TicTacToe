package controllers;

import play.mvc.*;

import ttt.game.GameOption;
import views.html.*;

import java.util.HashMap;

public class HomeController extends Controller {

    private HashMap<String, String> OPTIONS;

    public HomeController() {
        OPTIONS = new HashMap<>();
        for (GameOption option : GameOption.values()) {
            OPTIONS.put(option.key, option.title);
        }
    }

    public Result index() {
        return ok(index.render("Tic Tac Toe", OPTIONS));
    }

}
