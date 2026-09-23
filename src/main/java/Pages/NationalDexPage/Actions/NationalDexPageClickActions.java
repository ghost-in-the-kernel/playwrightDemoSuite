package Pages.NationalDexPage.Actions;
import Pages.NationalDexPage.NationalDexPageElements;
import com.microsoft.playwright.Page;

public class NationalDexPageClickActions {
    private final Page page;

    public NationalDexPageClickActions(Page page) {
        this.page = page;
    }

    public void pokemonLink(String pokemonName) {
        NationalDexPageElements.getPokemonLink(page, pokemonName).click();
    }

    public void pokemonRowByDexNumber(int dexNumber) {
        NationalDexPageElements.getPokemonRowByDexNumber(page, dexNumber).click();
    }
}
