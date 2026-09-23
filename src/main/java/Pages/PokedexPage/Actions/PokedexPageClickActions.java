package Pages.PokedexPage.Actions;

import Pages.PokemonFullListPage.PokemonFullListPage;
import Pages.PokedexPage.PokedexPageElements;
import com.microsoft.playwright.Page;

public class PokedexPageClickActions {
    private final Page page;

    public PokedexPageClickActions(Page page) {
        this.page = page;
    }

    public PokemonFullListPage goToMasterList() {
        PokedexPageElements.NATIONAL_DEX_LINK.locator(page).scrollIntoViewIfNeeded();
        PokedexPageElements.NATIONAL_DEX_LINK.locator(page).highlight();
        PokedexPageElements.NATIONAL_DEX_LINK.locator(page).click();
        System.out.println("Navigating to /all pokemon List...");
        page.waitForURL("**/pokedex/all");
        return new PokemonFullListPage(page);
    }
}