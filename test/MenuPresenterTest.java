import org.junit.Test;
import services.BoardMenuPresenter;
import services.GameMenuPresenter;

import static org.junit.Assert.assertEquals;

public class MenuPresenterTest {

    @Test
    public void convertsBoardOptionsToHash() {
        BoardMenuPresenter presenter = new BoardMenuPresenter();
        assertEquals("3x3", presenter.showOptions().get("3"));
        assertEquals("4x4", presenter.showOptions().get("4"));
    }

    @Test
    public void knowsBoardEndpoint() {
        BoardMenuPresenter presenter = new BoardMenuPresenter();
        assertEquals("/new-board", presenter.getEndpoint());
    }

    @Test
    public void convertsGameOptionsToHash() {
        GameMenuPresenter presenter = new GameMenuPresenter();
        assertEquals("Human v Human", presenter.showOptions().get("1"));
    }

    @Test
    public void knowsGameEndpoint() {
        GameMenuPresenter presenter = new GameMenuPresenter();
        assertEquals("/new-game", presenter.getEndpoint());
    }
}
