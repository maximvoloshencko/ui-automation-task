package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class TestUtils {

    public static final int MEDIUM_TIME_OUT = 10;

    public static WebElement await(WebDriver driver, By locator, int duration) {
        WebElement element = (new WebDriverWait(driver, duration))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
        element = (new WebDriverWait(driver, MEDIUM_TIME_OUT))
                .until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    public static WebElement await(WebDriver driver, By locator) {
        return await(driver, locator, MEDIUM_TIME_OUT);
    }

    public static String formatMessageWithList(String header, List<String> items) {
        StringBuilder failedAssertMessage = new StringBuilder();
        if(items.size() > 0) {
            failedAssertMessage.append(header);
            for(String item: items) {
                failedAssertMessage.append("\n\t* ".concat(item));
            }
        }
        return failedAssertMessage.toString();
    }

    public static SoftAssert softAssert() {
        return (new SoftAssert());
    }
}
