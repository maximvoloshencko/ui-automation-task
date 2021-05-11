package pages;

import modals.AddedToCartModal;
import models.ProductModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.AvailableFilterOptions;
import utils.TestUtils;

import java.util.List;

public class CatalogPage extends BasePage {

    private final By filterOptionsSizeL = By.cssSelector("#ul_layered_id_attribute_group_1 > li");
    private final By filterOptionsColorL = By.cssSelector("#ul_layered_id_attribute_group_3 > li");
    private final By filterOptionsCompositionL = By.cssSelector("#ul_layered_id_feature_5 > li");
    private final By filterOptionsStyleL = By.cssSelector("#ul_layered_id_feature_6 > li");
    private final By filterOptionsPropertiesL = By.cssSelector("#ul_layered_id_feature_7 > li");
    private final By filterOptionsAvailabilityL = By.cssSelector("#ul_layered_quantity_0 > li");
    private final By loaderIndicatorL = By.cssSelector(".product_list.grid.row img[src*=\"loader.gif\"]");

    private final By itemsL = By.cssSelector("#center_column ul.product_list.grid.row > li");

    private final AddedToCartModal addedToCartModal;

    public CatalogPage(WebDriver driver) {
        super(driver);
        addedToCartModal = new AddedToCartModal(driver);
    }

    public AddedToCartModal addedToCartModal() {
        return addedToCartModal;
    }

    public int getAmountOfItemsOnPage() {
        return driver.findElements(itemsL).size();
    }

    public ProductModel getItem(int index) {
        List<WebElement> items = driver.findElements(itemsL);
        if(index < items.size()) {
            return ProductModel.from(items.get(index));
        }
        throw new IndexOutOfBoundsException(
                "Attempt to access item with index " + index +
                        " out of list of " + getAmountOfItemsOnPage() + " items!");
    }

    public void openProduct(int index) {
        List<WebElement> items = (new WebDriverWait(driver, TestUtils.MEDIUM_TIME_OUT))
                .until(ExpectedConditions
                        .visibilityOfAllElements(driver.findElements(itemsL)));

        Actions selectItem = new Actions(driver);
        selectItem.moveToElement(items.get(index))
                .click().build().perform();
    }

    public void addToCart(int index) {
        List<WebElement> items = (new WebDriverWait(driver, TestUtils.MEDIUM_TIME_OUT))
            .until(ExpectedConditions
                    .visibilityOfAllElements(driver.findElements(itemsL)));

        if(index < items.size()) {
            WebElement item = (new WebDriverWait(driver, TestUtils.MEDIUM_TIME_OUT))
                .until(ExpectedConditions.visibilityOf(items.get(index)));

            Actions selectItem = new Actions(driver);
            selectItem.moveToElement(item)
                    .build().perform();

            WebElement addToCartButton = (new WebDriverWait(driver, TestUtils.MEDIUM_TIME_OUT))
                .until(ExpectedConditions
                        .visibilityOf(item.findElement(
                                By.cssSelector("a[title=\"Add to cart\"]"))));

            addToCartButton.click();
        }
    }

    public boolean isLoadingIndicatorOn() {
        return driver.findElement(loaderIndicatorL).isDisplayed();
    }

    public void waitWhileLoading() {
        (new WebDriverWait(driver, TestUtils.MEDIUM_TIME_OUT))
                .until(ExpectedConditions
                        .invisibilityOfElementLocated(loaderIndicatorL));
    }

    public int filterSize(AvailableFilterOptions.Size sizeOption) {
        List<WebElement> sizeOptions = driver.findElements(filterOptionsSizeL);
        int quantity = 0;

        for(WebElement option: sizeOptions) {
            String[] sValues = option.getText().split(" ");
            String sizeLabel = sValues[0];
            quantity = Integer.parseInt(sValues[1]
                    .replace("(", "")
                    .replace(")", ""));

            if(sizeOption.is(sizeLabel)) {
                Actions checkOption = new Actions(driver);
                checkOption.moveToElement(option.findElement(
                        By.tagName("input"))).click().build().perform();
                break;
            }
        }

        return quantity;
    }

    public int filterColor(AvailableFilterOptions.Color colorOption) {
        List<WebElement> colorOptions = driver.findElements(filterOptionsColorL);
        int quantity = 0;

        for(WebElement option: colorOptions) {
            String[] sValues = option.getText().split(" ");
            String colorLabel = sValues[0];
            quantity = Integer.parseInt(sValues[1]
                    .replace("(", "")
                    .replace(")", ""));

            if(colorOption.is(colorLabel)) {
                Actions checkOption = new Actions(driver);
                checkOption.moveToElement(option)
                        .click().build().perform();
                break;
            }
        }

        return quantity;
    }

    public int filterStyle(AvailableFilterOptions.Style styleOption) {
        List<WebElement> styleOptions = driver.findElements(filterOptionsStyleL);
        int quantity = 0;

        for(WebElement option: styleOptions) {
            String[] sValues = option.getText().split(" ");
            String styleLabel = sValues[0];
            quantity = Integer.parseInt(sValues[1]
                    .replace("(", "")
                    .replace(")", ""));

            if(styleOption.is(styleLabel)) {
                Actions checkOption = new Actions(driver);
                checkOption.moveToElement(option.findElement(
                        By.tagName("input"))).click().build().perform();
                break;
            }
        }

        return quantity;
    }

    public int filterComposition(AvailableFilterOptions.Composition compositionOption) {
        List<WebElement> componsitionOptions = driver.findElements(filterOptionsCompositionL);
        int quantity = 0;

        for(WebElement option: componsitionOptions) {
            String[] sValues = option.getText().split(" ");
            String compositionLabel = sValues[0];
            quantity = Integer.parseInt(sValues[1]
                    .replace("(", "")
                    .replace(")", ""));

            if(compositionOption.is(compositionLabel)) {
                Actions checkCompositon = new Actions(driver);
                checkCompositon.moveToElement(option.findElement(
                        By.tagName("input"))).click().build().perform();
                break;
            }
        }

        return quantity;
    }
}
