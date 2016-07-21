package services;

import java.util.HashMap;

public interface MenuPresenter {

    HashMap<String, String> showOptions();

    String getEndpoint();

    boolean isChosen();

    String getOption();
}
