package models;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.PriceParser;
import utils.AvailableFilterOptions;

import java.util.ArrayList;
import java.util.List;

public final class ProductModel implements PriceParser {
    private final By availabilityL = By.cssSelector("span.available-now");
    private final By nameL = By.cssSelector("h5[itemprop=\"name\"]");
    private final By priceL = By.cssSelector(".right-block span.price.product-price");
    private final By colorOptionsL = By.cssSelector(".color-list-container > ul > li > a");

    private final boolean availability;
    private final String name;
    private final String price;
    private final int quantity;
    private List<AvailableFilterOptions.Color> availableColors;

    private ProductModel(WebElement original) {
        availability = original.findElement(availabilityL)
                .getText().toLowerCase().equals("in stock");
        name = original.findElement(nameL).getText();
        price = original.findElement(priceL).getText();
        quantity = 1;

        availableColors = new ArrayList<>();
        List<WebElement> colors = original.findElements(colorOptionsL);
        for(WebElement c: colors) {
            String cValue = c.getAttribute("href").split("color-")[1];
            availableColors.add(AvailableFilterOptions.Color.from(cValue));
        }
    }

    public static ProductModel from(WebElement original) {
        return new ProductModel(original);
    }

    public boolean isAvailable() {
        return availability;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getNumericPrice() {
        return parsePrice(price);
    }

    @Override
    public String toString() {
        return availability + ":" + name + ":" + price;
    }
}