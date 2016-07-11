package controllers;

import play.mvc.*;

import views.html.*;

import java.util.HashMap;

public class HomeController extends Controller {

    private HashMap<String, String> OPTIONS;

    public HomeController() {
        OPTIONS = new HashMap<>();
        OPTIONS.put("1", "Human v Human");
        OPTIONS.put("5", "Perfect Player v Human");
        OPTIONS.put("4", "Human v Perfect Player");
        OPTIONS.put("6", "Perfect Player v Perfect Player");
        OPTIONS.put("2", "Human v Random Player");
        OPTIONS.put("3", "Random Player v Human");
        OPTIONS.put("9", "Random Player v Random Player");
    }

    public Result index() {
        return ok(index.render("Tic Tac Toe", OPTIONS));
    }

}
