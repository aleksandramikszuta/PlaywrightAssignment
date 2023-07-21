import com.microsoft.playwright.Page;
import com.microsoft.playwright.Locator;



public class HelpPage {

    protected Page page;
    protected static final String SEARCH_FIELD = "form[action='/help/en/']";
    protected static final String GENERAL = "general";
    protected static final String SYNDICATES = "syndicates-spvs";
    protected static final String FUNDS = "funds";
    protected static final String ANGEL_ROLL_UPS = "angel-roll-ups";
    protected static final String SEARCH_RESULT_LIST = "div[class='flex w-full flex-col gap-3']";
    protected static final String SEARCH_RESULT = "div[class='w-full']";

    protected Locator getElementById (String id) {
        return page.locator("div[id='" + id + "']");
    }
    public HelpPage(Page page) {
        this.page = page;
    }
    public void searchForTerm(String term) {
        page.locator(SEARCH_FIELD).locator("input").fill(term);
        page.locator(SEARCH_FIELD).locator("input").press("Enter");
    }

public HelpPage goToGeneral() {
    getElementById(GENERAL)
            .click();
    return new GeneralPage(page);
}

public HelpPage goToFunds() {
        getElementById(FUNDS)
                .click();
        return new FundsPage(page);
}
protected Locator getListedArticleByTitle(String title) {
        return page.locator("//div[contains(@class, 'flex')]/span[text()='" + title + "']");
}

public ArticlePage goToListedArticle(String title){
        getListedArticleByTitle(title)
                .click();
        return new ArticlePage(page);
}
}
