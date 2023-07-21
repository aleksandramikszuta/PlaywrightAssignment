import com.microsoft.playwright.Page;

public class Helpers {
    protected Page page;

    public Helpers(Page page) {
        this.page = page;
    }

    public String getAttributeValue(String locator, String attribute) {
        return page.locator(locator).getAttribute(attribute);
    }
}
