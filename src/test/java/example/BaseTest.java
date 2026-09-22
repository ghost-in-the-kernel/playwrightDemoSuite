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

        // 1. Determine if headless should be true
        boolean isHeadless = isHeadlessExecution();

        // 2. Adjust slowMo (keep slowMo in headed mode for visual debugging, 0 in headless)
        double slowMo = isHeadless ? 0 : 1000;

        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(isHeadless)
                        .setSlowMo(slowMo)
                        .setArgs(Arrays.asList(
                                "--disable-blink-features=AutomationControlled",
                                "--disable-infobars",
                                "--no-sandbox",
                                "--disable-setuid-sandbox",
                                "--start-maximized"
                        ))
        );
    }

    /**
     * Checks if headless mode should be enabled based on:
     * 1. System property: -Dheadless=true
     * 2. Environment variable: HEADLESS=true
     * 3. CI Pipeline detection: CI=true
     */
    private static boolean isHeadlessExecution() {
        // Option A: Command line parameter -Dheadless=true
        String sysProp = System.getProperty("headless");
        if (sysProp != null) {
            return Boolean.parseBoolean(sysProp);
        }
        // Option B: Environment variable HEADLESS=true
        String envVar = System.getenv("HEADLESS");
        if (envVar != null) {
            return Boolean.parseBoolean(envVar);
        }
        // Option C: Auto-detect CI environment (GitHub Actions, Jenkins, GitLab set CI=true automatically)
        String ciEnv = System.getenv("CI");
        if (ciEnv != null && Boolean.parseBoolean(ciEnv)) {
            return true;
        }
        // Default to headed (false) for local running
        return false;
    }

    @AfterAll
    static void closeBrowser() {
        if (playwright != null) {
            playwright.close();
        }
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(1920, 1080)
                .setLocale("en-US")
                .setTimezoneId("America/New_York")
                .setPermissions(Arrays.asList("geolocation"))
                .setExtraHTTPHeaders(Map.of(
                        "Accept-Language", "en-US,en;q=0.9"
                ))
        );

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