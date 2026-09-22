package example;

import com.microsoft.playwright.*;
import java.util.Arrays;
import java.util.Map;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseTest {
    protected static Playwright playwright;
    protected static Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
                        .setSlowMo(500)
                        .setArgs(Arrays.asList(
                                "--disable-blink-features=AutomationControlled",
                                "--disable-infobars",
                                "--no-sandbox",
                                "--disable-setuid-sandbox",
                                "--start-maximized"
                        ))
        );
    }

    @AfterAll
    static void closeBrowser() {
        if (playwright != null) {
            playwright.close();
        }
    }

    @BeforeEach
    void createContextAndPage() {
        // Create a context that mimics real world user (attempting to fool pokemon.com)
        context = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(1920, 1080)
                .setLocale("en-US")
                .setTimezoneId("America/New_York")
                .setPermissions(Arrays.asList("geolocation"))
                .setExtraHTTPHeaders(Map.of(
                        "Accept-Language", "en-US,en;q=0.9"
                ))
        );

        // Hiding the definitions (to try to hide from pokemon.com script detector)
        context.addInitScript("Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");
        context.addInitScript("Object.defineProperty(navigator, 'plugins', {get: () => [1, 2, 3, 4, 5]})");
        context.addInitScript("Object.defineProperty(navigator, 'languages', {get: () => ['en-US', 'en']})");
        context.addInitScript("window.chrome = { runtime: {} };");

        page = context.newPage();
    }

    @AfterEach
    void closeContext() {
        if (context != null) {
            context.close();
        }
    }
}