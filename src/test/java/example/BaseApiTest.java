package example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.util.HashMap;
import java.util.Map;

public class BaseApiTest {

    protected static Playwright playwright;
    protected ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    static void initPlaywright() {
        playwright = Playwright.create();
    }

    @AfterAll
    static void tearDownPlaywright() {
        if (playwright != null) {
            playwright.close();
        }
    }

    /**
     * Factory method to create a custom APIRequestContext for any external service.
     */
    protected APIRequestContext createRequestContext(String baseUrl, Map<String, String> extraHeaders) {
        Map<String, String> headers = new HashMap<>();
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) Chrome/120.0.0.0 Safari/537.36");
        headers.put("Accept", "application/json");

        if (extraHeaders != null) {
            headers.putAll(extraHeaders);
        }

        return playwright.request().newContext(
                new APIRequest.NewContextOptions()
                        .setBaseURL(baseUrl)
                        .setExtraHTTPHeaders(headers)
        );
    }
}