import org.junit.Before;
import org.junit.Test;
import services.BoardMenuPresenter;
import services.GameMenuPresenter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MenuPresenterTest {

    private BoardMenuPresenter boardMenuPresenter;

    @Before
    public void setUp() {
        boardMenuPresenter = new BoardMenuPresenter();
    }

    @Test
    public void convertsBoardOptionsToHash() {
        assertEquals("3x3", boardMenuPresenter.showOptions().get("3"));
        assertEquals("4x4", boardMenuPresenter.showOptions().get("4"));
    }

    @Test
    public void knowsBoardEndpoint() {
        assertEquals("/new-board", boardMenuPresenter.getEndpoint());
    }

    @Test
    public void knowsIfOptionHasBeenChosen() {
        boardMenuPresenter.chooseOption("3x3");
        assertTrue(boardMenuPresenter.isChosen());
    }

    @Test
    public void knowsTheChoiceName() {
        boardMenuPresenter.chooseOption("4x4");
        assertEquals("4x4", boardMenuPresenter.getOption());
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
