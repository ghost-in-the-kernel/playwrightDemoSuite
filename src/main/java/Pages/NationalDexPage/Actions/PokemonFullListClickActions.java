package Pages.NationalDexPage.Actions;
import Pages.NationalDexPage.PokemonFullListElements;
import com.microsoft.playwright.Page;

public class PokemonFullListClickActions {
    private final Page page;

    public PokemonFullListClickActions(Page page) {
        this.page = page;
    }

    public void pokemonLink(String pokemonName) {
        PokemonFullListElements.getPokemonLink(page, pokemonName).click();
    }

    public void pokemonRowByDexNumber(int dexNumber) {
        PokemonFullListElements.getPokemonRowByDexNumber(page, dexNumber).click();
    }
}
