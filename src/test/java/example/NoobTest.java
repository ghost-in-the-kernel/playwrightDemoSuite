package example;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.LoadState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NoobTest {

    @Test
    void testCompletePokemonFlow() {
        try (Playwright playwright = Playwright.create();
                Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                        .setSlowMo(1000)
                        .setHeadless(false));
                Page page = browser.newPage()) {

            page.navigate("https://pokemondb.net/");
            page.waitForLoadState(LoadState.DOMCONTENTLOADED);

            page.navigate("https://pokemondb.net/pokedex/all");
            page.waitForURL("**/pokedex/all");

            boolean isBulbasaurVisible = page.locator("a.ent-name:has-text('Bulbasaur')")
                    .first()
                    .isVisible();

            Assertions.assertTrue(isBulbasaurVisible, "Bulbasaur should be visible on the National Pokedex page");

            System.out.println("Standalone test passed successfully!");
        }
    }
}
