package modals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.TestUtils;

public class AddedToCartModal extends BaseModal {

    private final By totalProductsL = By.cssSelector(".layer_cart_cart > .layer_cart_row:nth-of_type(1) > span");
    private final By totalShippingL = By.cssSelector(".layer_cart_cart > .layer_cart_row:nth-of_type(2) > span");
    private final By totalL = By.cssSelector(".layer_cart_cart > .layer_cart_row:nth-of_type(3) > span");
    private final By productName = By.id("layer_cart_product_title");
    private final By attributesL = By.id("layer_cart_product_attributes");
    private final By quantityL = By.id("layer_cart_product_quantity");

    private final By overlayBackgroundL = By.cssSelector(".layer_cart_overlay");
    private final By continueShoppingButtonL = By.cssSelector("span[title=\"Continue shopping\"]");
    private final By proceedToCheckoutButtonL = By.cssSelector("a[title=\"Proceed to checkout\"]");

    public AddedToCartModal(WebDriver driver) {
        super(driver);
    }

    public void continueShopping() {
        (new WebDriverWait(driver, MEDIUM_TIME_OUT))
                .until(ExpectedConditions
                        .visibilityOfAllElementsLocatedBy(overlayBackgroundL));
        TestUtils.await(driver, continueShoppingButtonL).click();
        (new WebDriverWait(driver, MEDIUM_TIME_OUT))
                .until(ExpectedConditions
                        .invisibilityOfElementLocated(overlayBackgroundL));
    }

    public void proceedToCheckout() {
        TestUtils.await(driver, proceedToCheckoutButtonL).click();
    }
}
