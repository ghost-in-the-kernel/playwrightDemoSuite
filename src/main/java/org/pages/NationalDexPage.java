package org.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class NationalDexPage{
    private final Locator bulbasaurLink;

    public NationalDexPage(Page page) {
        this.bulbasaurLink = page.locator("a[href='/pokedex/bulbasaur']").first();
    }

    public void assertBulbasaurIsVisible() {
        System.out.println("Asserting that Bulbasaur is visible...");
        assertThat(bulbasaurLink).isVisible();
        System.out.println("Assertion Passed: Bulbasaur found!");
    }
}