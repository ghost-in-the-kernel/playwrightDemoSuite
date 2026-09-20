package example;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.LoadState;
import org.junit.jupiter.api.Test;
import org.pages.NationalDexPage;
import org.pages.PokedexPage;
import org.pages.PokemonHomePage;

public class PokemonTest extends example.BaseTest {
    @Test
    void PokeDexNav() {
        // 1. Start at the Home Page
        PokemonHomePage homePage = new PokemonHomePage(page);
        homePage.page.navigate("https://pokemondb.net/");
        homePage.page.waitForLoadState(LoadState.DOMCONTENTLOADED);

        PokedexPage pokedexPage = homePage.goToPokedex();
        pokedexPage.page.waitForURL("**/pokedex");
        System.out.println("Successfully navigated to pokedex top!");
        page.waitForTimeout(1000);

        NationalDexPage NationalDexPage= pokedexPage.goToNationalDex();
        pokedexPage.page.waitForURL("**/national");
        page.waitForTimeout(1000);
        System.out.println("Successfully navigated to National pokedex!");

        NationalDexPage.assertBulbasaurIsVisible();

    }

    @Test //not separated into separate concerns.
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