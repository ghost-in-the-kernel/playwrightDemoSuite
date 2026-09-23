package Pages.PokemonFullListPage;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public enum PokemonFullListElements {
    POKEDEX_TABLE(page -> page.locator("//table[@id='pokedex']")),
    Bulbasaur(page -> page.locator("//table[@id='pokedex']//a[@href='/pokedex/bulbasaur']")),
    Charmander(page -> page.locator("//table[@id='pokedex']//a[text()='Charmander']")),
    ;

    @FunctionalInterface
    public interface LocatorProvider {
        Locator get(Page page);
    }

    private final LocatorProvider provider;

    PokemonFullListElements(LocatorProvider provider) {
        this.provider = provider;
    }

    public Locator locator(Page page) {
        return this.provider.get(page);
    }

    // ==========================================
    // String Normalization Helper
    // ==========================================

    /**
     * Capitalizes the first letter and lowercases the rest.
     * Example: "bulbasuar" -> "Bulbasuar", "CHARMANDER" -> "Charmander"
     */
    private static String normalizePokemonName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return name;
        }
        String trimmed = name.trim();
        return trimmed.substring(0, 1).toUpperCase() + trimmed.substring(1).toLowerCase();
    }

    // ==========================================
    // Static Helper Methods (Using Normalization)
    // ==========================================

    public static Locator getPokemonLink(Page page, String pokemonName) {
        String formattedName = normalizePokemonName(pokemonName);
        return page.locator(String.format("//table[@id='pokedex']//a[text()='%s']", formattedName));
    }

    public static Locator getPokemonRow(Page page, String pokemonName) {
        String formattedName = normalizePokemonName(pokemonName);
        return page.locator(String.format("//table[@id='pokedex']//tr[td/a[text()='%s']]", formattedName));
    }

    public static Locator getPokemonTypes(Page page, String pokemonName) {
        String formattedName = normalizePokemonName(pokemonName);
        return page.locator(String.format("//table[@id='pokedex']//tr[td/a[text()='%s']]//td[contains(@class, 'cell-icon')]/a", formattedName));
    }

    public static Locator getPokemonStat(Page page, String pokemonName, PokemonStat stat) {
        String formattedName = normalizePokemonName(pokemonName);
        return page.locator(String.format("//table[@id='pokedex']//tr[td/a[text()='%s']]/td[%d]", formattedName, stat.getColumnIndex()));
    }

    public static Locator getPokemonRowByDexNumber(Page page, int dexNumber) {
        String formattedId = String.format("%04d", dexNumber);
        return page.locator(String.format("//table[@id='pokedex']//tr[td/span[text()='%s']]", formattedId));
    }

    public enum PokemonStat {
        TOTAL(3),
        HP(4),
        ATTACK(5),
        DEFENSE(6),
        SP_ATTACK(7),
        SP_DEFENSE(8),
        SPEED(9);

        private final int columnIndex;

        PokemonStat(int columnIndex) {
            this.columnIndex = columnIndex;
        }

        public int getColumnIndex() {
            return columnIndex;
        }
    }
}