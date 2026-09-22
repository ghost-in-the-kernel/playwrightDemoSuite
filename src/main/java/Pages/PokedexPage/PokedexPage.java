package Pages.PokedexPage;

import Pages.NationalDexPage.NationalDexPage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class PokedexPage {
    public final Page page;
    private final Locator nationalDexLink;

    public PokedexPage(Page page) {
        this.page = page;

        this.nationalDexLink = page.locator("a[href='/pokedex/national']")
                .filter(new Locator.FilterOptions().setHas(page.locator("strong")));
    }

    public NationalDexPage goToNationalDex() {
        System.out.println("Navigating to National Dex...");

        nationalDexLink.scrollIntoViewIfNeeded();
        nationalDexLink.highlight();
        page.waitForTimeout(1000);

        nationalDexLink.click();

        page.waitForURL("**/national");
        return new NationalDexPage(page);
    }
}