import com.microsoft.playwright.*;
import org.testng.annotations.*;

public class HelpTests {

    protected static BrowserContext browserContext;
    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;

    private static String HELP_URL = "https://www.bunch.capital/help/en";
    private Page page;
    private HelpPage helpPage;

    private HelpPageAsserts helpPageAsserts;
    private ArticlePageAsserts articlePageAsserts;

    @BeforeSuite
    public void initBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @BeforeTest
    public void goToHelpPage() {
        context = browser.newContext(new Browser.NewContextOptions().setBaseURL(HELP_URL));
        page = context.newPage();
        helpPage = new HelpPage(page);
        helpPageAsserts = new HelpPageAsserts(page);
        articlePageAsserts = new ArticlePageAsserts(page);
        page.navigate("");
    }

    @AfterTest
    public void closeContext() {
        context.close();
    }

    @AfterSuite
    public void closeBrowser()
    {
        browser.close();
        playwright.close();
    }

    @Test
    public void searchForHelpTopicYieldsResult() {
        helpPage
                .searchForTerm("SPV");
        helpPageAsserts
                .assertThatSearchResultsExist();
    }

    @Test
    public void searchForInvalidTopicYieldsNoResult() {
        helpPage
                .searchForTerm("qwertyuio");
        helpPageAsserts
                .assertThatSearchResultsDoNotExist();
    }

    @Test
    public void clickingArticleNavigatesToProperArticlePage(){
        String title = "Why was bunch founded?";
        helpPage
                .goToGeneral()
                .goToListedArticle(title);
        articlePageAsserts
                .assertThatTitleIsProper(title);
    }
}
