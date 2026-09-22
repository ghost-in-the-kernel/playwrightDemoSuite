package example;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.LoadState;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import Pages.NationalDexPage.NationalDexPage;
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

        //2. navigate to pokedex page
        PokedexPage pokedexPage = homePage.goToPokedex();
        System.out.println("Successfully navigated to pokedex top!");

        //3. navigate to national dex page
        NationalDexPage NationalDexPage= pokedexPage.goToNationalDex();
        System.out.println("Successfully navigated to National pokedex!");

        //perform assertion
        NationalDexPage.assertBulbasaurIsVisible();
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