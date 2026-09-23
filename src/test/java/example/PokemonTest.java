package example;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import java.nio.file.Paths;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import Pages.NationalDexPage.PokemonFullListPage;
import Pages.PokedexPage.PokedexPage;
import Pages.PokemonHomePage.PokemonHomePage;

public class PokemonTest extends example.BaseTest {
    @Test
    @Tag("regression")
    void PokeDexNav() {
        // 1. Start at the Home Page
        PokemonHomePage homePage = new PokemonHomePage(page);
        homePage.page.navigate("https://pokemondb.net/");
        homePage.page.waitForLoadState(LoadState.DOMCONTENTLOADED);
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("target/screenshots/homepage.png"))
                .setFullPage(true));

        //2. navigate to pokedex page
        PokedexPage pokedexPage = homePage.goToPokedex();
        System.out.println("Successfully navigated to pokedex top!");

        //3. navigate to national dex page
        PokemonFullListPage PokemonFullListPage = pokedexPage.click().goToMasterList();
        System.out.println("Successfully navigated to National pokedex!");

        //perform assertion
        PokemonFullListPage.verify().pokemon("Bulbasaur").isVisible();
        PokemonFullListPage.verify().pokemon("Charmander").isVisible();
        PokemonFullListPage.verify().pokemon("Squirtle").isVisible();
        PokemonFullListPage.verify().pokemon("Venusaur").isVisible();
        PokemonFullListPage.verify().pokemon("Charizard").isVisible();
    }

    @Test //not separated into separate concerns.
    @Tag("smoke")
    void navigateToScarlet() {
        page.navigate("https://pokemondb.net/");

        Locator gamesMenu = page.locator(".main-menu-item").filter(
                new Locator.FilterOptions().setHasText("Games")
        );

        gamesMenu.locator(".main-menu-heading").hover();

        Locator gameLink = page.locator("a[href='/scarlet-violet']");

        gameLink.click();

        page.waitForURL("**/scarlet-violet");
        System.out.println("Successfully navigated to Scarlet & Violet!");
    }
}