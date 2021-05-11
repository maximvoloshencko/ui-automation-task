package pages;

import modals.AddedToCartModal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.AvailableFilterOptions;
import utils.TestUtils;

import java.util.List;

public class ProductPage extends BasePage {
    private final By priceL = By.id("our_price_display");
    private final By quantityInputL = By.id("quantity_wanted");
    private final By sizeSelectL = By.id("group_1");
    private final By colorListL = By.cssSelector("#color_to_pick_list > ul > li > a");
    private final By compositionL = By.cssSelector(".table-data-sheet > tbody > tr:nth-of-type(1) > td + td");
    private final By styleL = By.cssSelector(".table-data-sheet > tbody > tr:nth-of-type(2) > td + td");
    private final By propertiesL = By.cssSelector(".table-data-sheet > tbody > tr:nth-of-type(3) > td + td");

    private final By addToCartButonL = By.name("Submit");

    private final AddedToCartModal addedToCartModal;

    public ProductPage(WebDriver driver) {
        super(driver);
        addedToCartModal = new AddedToCartModal(driver);
    }

    public AddedToCartModal addedToCartModal() {
        return addedToCartModal;
    }

    private List<WebElement> getAvailableColors() {
        return driver.findElements(colorListL);
    }

    public void selectColor(AvailableFilterOptions.Color color) {
        List<WebElement> availableColors = getAvailableColors();
        for(WebElement availableColor: availableColors) {
            String colorValue = availableColor.getAttribute("title");
            if(color.is(colorValue)) {
                availableColor.click();
            }
        }
    }

    public void selectSize(AvailableFilterOptions.Size size) {
        WebElement sizeSelect = driver.findElement(sizeSelectL);
        sizeSelect.click();
        (new Select(sizeSelect))
                .selectByVisibleText(size.name());
    }

    public void addToCart() {
        TestUtils.await(driver, addToCartButonL).click();
    }
}
