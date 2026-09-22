package PokeTestsTopLevelActions;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;

public class WaitActions {

    public static final double THIRTY_FOUR_SECONDS_MS = 34_000;

    private WaitActions() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Waits for the active document readyState to reach 'DOMContentLoaded' or 'LOAD'.
     *
     * @param page the Playwright Page instance
     * @return true if successful
     */
    public static boolean waitForDocumentReadyStateComplete(Page page) {
        try {
            page.waitForLoadState(
                    LoadState.DOMCONTENTLOADED,
                    new Page.WaitForLoadStateOptions().setTimeout(THIRTY_FOUR_SECONDS_MS)
            );
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
