import com.microsoft.playwright.Frame;
import com.microsoft.playwright.Page;

public class HelpPage {

    private Page page;
    private String SEARCH_FIELD = "form[action=\"/help/en/\"]";

    public HelpPage(Page page) {
        this.page = page;
    }

    public void searchForTerm(String term) {
        page.locator(SEARCH_FIELD).fill(term);
    }
}
