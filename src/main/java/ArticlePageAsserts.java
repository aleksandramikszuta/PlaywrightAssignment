
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.assertj.core.api.Assertions;
import org.testng.Assert;


import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ArticlePageAsserts extends ArticlePage {

    private Helpers helpers;
    public ArticlePageAsserts(Page page) {
        super(page);
        helpers = new Helpers(page);
    }


protected Locator getTitleByHeader(String title){
        return page.locator("//header[text()='" + title + "']");
}
    public ArticlePageAsserts assertThatTitleIsProper(String title) {
        int titleHeadersFound = getTitleByHeader(title).count();
        Assert.assertEquals(titleHeadersFound, 1);
        return this;
    }

    public ArticlePageAsserts assertThatOnlyNeutralFaceIsChecked() {
        Assertions.assertThat(helpers.getAttributeValue(NEUTRAL_FACE, "aria-pressed")).isEqualTo("true");
        return this;
    }
}
