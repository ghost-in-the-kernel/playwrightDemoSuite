package Pages.NationalDexPage.Actions;

import Pages.NationalDexPage.NationalDexPageElements;
import com.microsoft.playwright.Page;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class NationalDexPageVerifyActions {
    private final Page page;

    public NationalDexPageVerifyActions(Page page) {
        this.page = page;
    }

    public PokemonAssertion pokemon(String pokemonName) {
        return new PokemonAssertion(
                NationalDexPageElements.getPokemonLink(page, pokemonName),
                pokemonName
        );
    }
}