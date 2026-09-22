package Pages.NationalDexPage;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class NationalDexPage{
    private final Locator bulbasaurLink;
    private final Page page;

    public NationalDexPage load() {
        page.navigate("https://pokemondb.net/pokedex/all");
        return isLoaded();
    }
    public NationalDexPage isLoaded(){
        page.waitForURL("**/pokedex/all");
        page.waitForLoadState(LoadState.DOMCONTENTLOADED);
        assertThat(bulbasaurLink).isVisible();
        return this;
    }

    public NationalDexPage(Page page) {
        this.bulbasaurLink = page.locator("a[href='/pokedex/bulbasaur']").first();
        this.page=page;
    }

    public void assertBulbasaurIsVisible() {
        System.out.println("Asserting that Bulbasaur is visible...");
        assertThat(bulbasaurLink).isVisible();
        System.out.println("Assertion Passed: Bulbasaur found!");
    }
}