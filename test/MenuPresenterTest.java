import org.junit.Before;
import org.junit.Test;
import services.GameMenuPresenter;

import static org.junit.Assert.assertEquals;

public class MenuPresenterTest {

    private GameMenuPresenter gameMenuPresenter;

    @Before
    public void setUp() {
        gameMenuPresenter = new GameMenuPresenter();
    }

    @Test
    public void convertsGameOptionsToHash() {
        assertEquals("Human v Human", gameMenuPresenter.showOptions().get("1"));
    }

}
