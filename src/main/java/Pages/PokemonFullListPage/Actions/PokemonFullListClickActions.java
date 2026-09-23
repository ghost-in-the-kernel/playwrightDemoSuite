package Pages.PokemonFullListPage.Actions;
import Pages.PokemonFullListPage.PokemonFullListElements;
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
