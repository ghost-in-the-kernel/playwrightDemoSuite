package Pages.PokemonFullListPage.Actions;

import Pages.PokemonFullListPage.PokemonFullListElements;
import com.microsoft.playwright.Page;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class PokemonFullListVerifyActions {
    private final Page page;

    public PokemonFullListVerifyActions(Page page) {
        this.page = page;
    }

    public PokemonAssertion pokemon(String pokemonName) {
        return new PokemonAssertion(
                PokemonFullListElements.getPokemonLink(page, pokemonName),
                pokemonName
        );
    }
}