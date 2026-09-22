package example;

import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
public class PokeApiTest extends BaseApiTest {

    private APIRequestContext pokeClient;

    @BeforeEach
    void setupClient() {
        // Initialize request context targeting PokeAPI base URL
        pokeClient = createRequestContext("https://pokeapi.co", null);
    }

    @AfterEach
    void tearDownClient() {
        if (pokeClient != null) {
            pokeClient.dispose();
        }
    }

    @Test
    @Tag("api")
    void testGetPikachuDetails() throws IOException {
        System.out.println("\n========================================");
        System.out.println("   Pikachu API test");
        System.out.println("========================================\n");
        APIResponse response = pokeClient.get("/api/v2/pokemon/pikachu");
        // 1. Assert status code
        assertEquals(200, response.status(), "Expected HTTP 200 from PokeAPI");
        // 2. Parse JSON response
        JsonNode json = objectMapper.readTree(response.text());
        // 3. Assert specific data points
        assertEquals(25, json.get("id").asInt(), "Pikachu's Pokedex ID should be 25");
        assertEquals("pikachu", json.get("name").asText());
        // Assert height
        assertEquals(4, json.get("height").asInt());
        assertEquals(60, json.get("weight").asInt());
        // Verify primary type is "electric"
        String primaryType = json.get("types").get(0).get("type").get("name").asText();
        assertEquals("electric", primaryType);
        System.out.printf("Fetched: %s (ID: #%d) | Type: %s | Weight: %d kg%n",
                json.get("name").asText().toUpperCase(),
                json.get("id").asInt(),
                primaryType,
                json.get("weight").asInt() / 10);
    }

    @Test
    @Tag("api")
    void MewtoTest() throws IOException {
        System.out.println("\n========================================");
        System.out.println("   Mewto Details Test(should fail and throw exception)");
        System.out.println("========================================\n");
        APIResponse response = pokeClient.get("/api/v2/pokemon-species/mewtwo");

        assertEquals(200, response.status());

        JsonNode json = objectMapper.readTree(response.text());

        // Assert legendary status flags
        assertTrue(json.get("is_legendary").asBoolean(), "Mewtwo should be flagged as legendary");
        assertFalse(json.get("is_mythical").asBoolean(), "Mewtwo is legendary, not mythical");
        assertEquals("rare", json.get("growth_rate").get("name").asText());
    }
}