package Pages.NationalDexPage;

import Pages.NationalDexPage.Actions.PokemonFullListClickActions;
import Pages.NationalDexPage.Actions.PokemonFullListVerifyActions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import org.paulJensen.qa.pokeTests.ClickableAction;
import org.paulJensen.qa.pokeTests.VerifiableAction;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class PokemonFullListPage implements
        ClickableAction<PokemonFullListClickActions>,
        VerifiableAction<PokemonFullListVerifyActions> {
    private final Page page;

    public PokemonFullListPage(Page page) {
        System.out.println(page.title());
        this.page=page;
    }

    public PokemonFullListPage load() {
        page.navigate("https://pokemondb.net/pokedex/all");
        return isLoaded();
    }
    public PokemonFullListPage isLoaded(){
        page.waitForURL("**/pokedex/all");
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
        assertThat(PokemonFullListElements.POKEDEX_TABLE.locator(page)).isVisible();
        return this;
    }

    @Override
    public PokemonFullListClickActions click(){
        return new PokemonFullListClickActions(page);
    }
    @Override
    public PokemonFullListVerifyActions verify() {
        return new PokemonFullListVerifyActions(page);
    }
}
