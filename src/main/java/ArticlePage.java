import com.microsoft.playwright.Page;

public class ArticlePage extends HelpPage {
    public ArticlePage(Page page) {
        super(page);}
    protected static final String NEUTRAL_FACE = "button[aria-label='Neutral Reaction']";

    public ArticlePage giveNeutralRating(){
        page.locator(NEUTRAL_FACE).scrollIntoViewIfNeeded();
        page.locator(NEUTRAL_FACE).click();
        return this;
    }
}
