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
        helpPage.searchForTerm("SPV");
        helpPageAsserts.assertThatSearchResultsExist();
    }

    @Test
    public void searchForInvalidTopicYieldsNoResult() {
        helpPage.searchForTerm("qwertyyuio");
        helpPageAsserts.assertThatSearchResultsDoNotExist();
    }

}
