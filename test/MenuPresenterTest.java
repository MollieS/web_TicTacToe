import org.junit.Before;
import org.junit.Test;
import presenters.GameMenuPresenter;

import static org.junit.Assert.assertEquals;

public class MenuPresenterTest {

    private GameMenuPresenter gameMenuPresenter;

    @Before
    public void setUp() {
        gameMenuPresenter = new GameMenuPresenter();
    }

    @Test
    public void convertsGameOptionsToList() {
        assertEquals("Human v Human", gameMenuPresenter.showOptions().get(0));
    }

}
