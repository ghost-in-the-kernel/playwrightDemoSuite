package Pages.NationalDexPage.Actions;

import com.microsoft.playwright.Locator;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class PokemonAssertion {
    private final Locator locator;
    private final String pokemonName;

    public PokemonAssertion(Locator locator, String pokemonName) {
        this.locator = locator;
        this.pokemonName = pokemonName;
    }

    public PokemonAssertion isVisible() {
        System.out.println("Asserting that " + pokemonName + " is visible...");
        assertThat(locator.first()).isVisible();
        System.out.println("Assertion Passed: " + pokemonName + " found!");
        return this;
    }

    public PokemonAssertion isNotVisible() {
        System.out.println("Asserting that " + pokemonName + " is not visible...");
        assertThat(locator).hasCount(0);
        return this;
    }
}