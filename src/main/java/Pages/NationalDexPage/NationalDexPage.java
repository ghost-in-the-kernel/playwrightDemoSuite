package Pages.NationalDexPage;

import Pages.NationalDexPage.Actions.NationalDexPageClickActions;
import Pages.NationalDexPage.Actions.NationalDexPageVerifyActions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import org.paulJensen.qa.pokeTests.ClickableAction;
import org.paulJensen.qa.pokeTests.VerifiableAction;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class NationalDexPage implements
        ClickableAction<NationalDexPageClickActions>,
        VerifiableAction<NationalDexPageVerifyActions> {
    private final Page page;

    public NationalDexPage(Page page) {
        System.out.println(page.title());
        this.page=page;
    }

    public NationalDexPage load() {
        page.navigate("https://pokemondb.net/pokedex/all");
        return isLoaded();
    }
    public NationalDexPage isLoaded(){
        page.waitForURL("**/pokedex/all");
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
        assertThat(NationalDexPageElements.POKEDEX_TABLE.locator(page)).isVisible();
        return this;
    }

    @Override
    public NationalDexPageClickActions click(){
        return new NationalDexPageClickActions(page);
    }
    @Override
    public NationalDexPageVerifyActions verify() {
        return new NationalDexPageVerifyActions(page);
    }
}
