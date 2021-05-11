package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.TestUtils;

import java.util.List;

public class CartSummaryPage extends BasePage implements PriceParser {

    private static final class ItemLocators {
        public static final By name = By.cssSelector("p.product-name");
        public static final By availability = By.cssSelector("td.cart_avail");
        public static final By unitPrice = By.cssSelector("td[data-title=\"Unit price\"]");
        public static final By quantity = By.cssSelector("td.cart_quantity > input[type=\"text\"]");
        public static final By totalPrice = By.cssSelector("td.cart_total");
        public static final By deleteItemButtonL = By.cssSelector("a[title=\"Delete\"]");
    }

    private final By itemsInCartL = By.cssSelector("#cart_summary > tbody > tr");
    private final By emptyCartWartingL = By.cssSelector("#order_step + p.alert.alert-warning");

    private final By totalPriceL = By.id("total_product");
    private final By totalShippingL = By.id("total_shipping");
    private final By totalPriceWithTaxL = By.id("total_price_without_tax");
    private final By taxL = By.id("total_tax");
    private final By finalTotalPriceL = By.id("total_price");

    public CartSummaryPage(WebDriver driver) {
        super(driver);
    }

    public int getNumberOfItems() {
        return driver.findElements(itemsInCartL).size();
    }

    public void deleteItem(int index) {
        if(getNumberOfItemsInCart() > index) {
            List<WebElement> items = driver.findElements(itemsInCartL);

            WebElement itemToDelete = items.get(index);
            itemToDelete.findElement(ItemLocators.deleteItemButtonL).click();

            (new WebDriverWait(driver, TestUtils.MEDIUM_TIME_OUT))
                    .until(ExpectedConditions
                            .invisibilityOf(itemToDelete));
        }else {
            throw new IndexOutOfBoundsException(
                    "Attempt to remove item(" + index + ") from card with "
                            + getNumberOfItems() + " items!");
        }
    }

    public void deleteAllItems() {
        int numberOfItems = getNumberOfItems();
        if(numberOfItems == 0) {
            return;
        }
        for(int i = numberOfItems - 1; i > 0; i--) {
            deleteItem(i);
        }
        deleteItem(0);
    }

    public boolean isCartEmptyWarningPresent() {
        waitTillPageIsReady();
        if(!driver.findElements(emptyCartWartingL).isEmpty()) {
            return driver.findElement(emptyCartWartingL).isDisplayed();
        }
        return false;
    }

    public int getTotalPrice() {
        return parsePrice(driver.findElement(totalPriceL).getText());
    }

    public int getTotalShipping() {
        return parsePrice(driver.findElement(totalShippingL).getText());
    }

    public int getTotalPriceWithShipping() {
        return parsePrice(driver.findElement(totalPriceWithTaxL).getText());
    }

    public int getTax() {
        return parsePrice(driver.findElement(taxL).getText());
    }

    public int getFinalTotalPrice() {
        return parsePrice(driver.findElement(finalTotalPriceL).getText());
    }
}