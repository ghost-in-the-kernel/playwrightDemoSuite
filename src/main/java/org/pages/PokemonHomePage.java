package org.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class PokemonHomePage {
    public final Page page;
    private final Locator dataMenu;
    private final Locator pokedexLink;

    public PokemonHomePage(Page page) {
        this.page = page;

        this.dataMenu = page.locator(".main-menu-item").filter(
                new Locator.FilterOptions().setHasText("Data")
        );

        this.pokedexLink = dataMenu.locator("a[href='/pokedex']");
    }

    public PokedexPage goToPokedex() {
        System.out.println("Navigating to Pokedex...");

        dataMenu.highlight();
        page.waitForTimeout(1000);

        dataMenu.locator(".main-menu-heading").hover();
        page.waitForTimeout(1000); // Wait for dropdown

        pokedexLink.highlight();
        page.waitForTimeout(1000);

        pokedexLink.click();
        page.waitForURL("**/pokedex");
        return new PokedexPage(page);
    }
}