package Pages.PokedexPage;

import Pages.PokedexPage.Actions.PokedexPageClickActions;
import Pages.PokedexPage.Actions.PokedexPageVerifyActions;
import com.microsoft.playwright.Page;
import org.paulJensen.qa.pokeTests.ClickableAction;
import org.paulJensen.qa.pokeTests.VerifiableAction;

public class PokedexPage implements
        ClickableAction<PokedexPageClickActions>,
        VerifiableAction<PokedexPageVerifyActions> {
    public final Page page;
    public PokedexPage(Page page) {
        this.page = page;
    }
    @Override
    public PokedexPageClickActions click() {
        return new PokedexPageClickActions(page);
    }
    @Override
    public PokedexPageVerifyActions verify() {
        return new PokedexPageVerifyActions(page);
    }
}