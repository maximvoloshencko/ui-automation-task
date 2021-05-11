package modals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.PriceParser;

public class BaseModal implements PriceParser {

    protected final WebDriver driver;
    protected final int MEDIUM_TIME_OUT = 10;

    private final By closeL = By.cssSelector("span[title=\"Close window\"]");

    public BaseModal(WebDriver driver) {
        this.driver = driver;
    }

    public void close() {

    }

    // REMOVE THIS !!!!!!!!!!!!!!!

    protected WebElement await(By locator, int duration) {
        WebElement element = (new WebDriverWait(driver, duration))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
        element = (new WebDriverWait(driver, MEDIUM_TIME_OUT))
                .until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    protected WebElement await(By locator) {
        return await(locator, MEDIUM_TIME_OUT);
    }
}
