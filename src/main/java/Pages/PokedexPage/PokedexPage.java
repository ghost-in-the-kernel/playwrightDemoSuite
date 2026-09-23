package Pages.PokedexPage;

import Pages.NationalDexPage.NationalDexPage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class PokedexPage {
    public final Page page;
    private final Locator nationalDexLink;

    public PokedexPage(Page page) {
        this.page = page;

        // Locates the link by its accessible text "All Pokémon"
        this.nationalDexLink = page.getByRole(AriaRole.LINK,
                new Page.GetByRoleOptions().setName("All Pokémon"));
    }

    public NationalDexPage goToMasterList() {
        System.out.println("Navigating to Master List...");

        nationalDexLink.scrollIntoViewIfNeeded();
        nationalDexLink.highlight();
        page.waitForTimeout(1000);

        nationalDexLink.click();

        page.waitForURL("**/pokedex/all");
        return new NationalDexPage(page);
    }
}