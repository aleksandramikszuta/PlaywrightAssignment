import com.microsoft.playwright.Page;
import org.testng.Assert;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class HelpPageAsserts extends HelpPage {
    public HelpPageAsserts(Page page) {
        super(page);
    }
    public HelpPageAsserts assertThatSearchResultsExist(){
        int count = page.locator(SEARCH_RESULT).count();
        Assert.assertTrue(count > 0);
        return this;
    }
}
