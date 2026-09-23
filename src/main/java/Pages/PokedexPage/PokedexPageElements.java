package Pages.PokedexPage;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public enum PokedexPageElements {
    MasterListLink(page -> page.getByRole(AriaRole.LINK,
            new Page.GetByRoleOptions().setName("All Pokémon")).first());

    PokedexPageElements(LocatorProvider provider) {
        this.provider = provider;
    }

    @FunctionalInterface
    public interface LocatorProvider {
        Locator get(Page page);
    }

    private final LocatorProvider provider;


    public Locator locator(Page page) {
        return this.provider.get(page);
    }
}
