package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.TestUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final String OK_COLOR = "rgba(53, 179, 63, 1)";
    protected final String ERROR_COLOR = "rgba(241, 51, 64, 1)";

    private final By pageContextL = By.id("page");
    private final By signInLinkL = By.cssSelector("div.header_user_info > a.login");
    private final By signOutLinkL = By.cssSelector("div.header_user_info > a.logout");
    private final By accountLinkL = By.cssSelector("div.header_user_info > a.account");
    private final By navigationBarCategoryL = By.cssSelector("#block_top_menu > ul > li");
    private final By numberOfItemsInCartL = By.cssSelector("div.shopping_cart span.ajax_cart_quantity");
    private final By emptyCartIndicatorL = By.cssSelector("div.shopping_cart span.ajax_cart_no_product");
    private final By cartLinkL = By.cssSelector("a[title=\"View my shopping cart\"]");

    private final By alertBlockL = By.cssSelector("div.alert.alert-danger");
    private final By alertErrorItemsL = By.cssSelector("ol > li");
    private final By alertSuccessL = By.cssSelector("p.alert.alert-success");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.driver.manage().window().maximize();
    }

    protected void waitTillPageIsReady() {
        TestUtils.await(driver, pageContextL);
    }

    public void back() {
        driver.navigate().back();
        waitTillPageIsReady();
    }

    public void forward() {
        driver.navigate().forward();
        waitTillPageIsReady();
    }

    public void refresh() {
        driver.navigate().refresh();
        waitTillPageIsReady();
    }

    public void toSignInPage() {
        WebElement signInLink = (new WebDriverWait(driver, TestUtils.MEDIUM_TIME_OUT))
                .until(ExpectedConditions.presenceOfElementLocated(signInLinkL));
        signInLink.click();
    }

    public boolean isLoggedIn() {
        List<WebElement> accountLink = driver.findElements(accountLinkL);
        return accountLink.size() > 0;
    }

    public void signOut() {
        driver.findElement(signOutLinkL).click();
    }

    public boolean isLoggedInAs(String firstName, String lastName) {
        if(isLoggedIn()) {
            String accountLinkText = driver.findElement(accountLinkL).getText();
            return (firstName + " " + lastName).contains(accountLinkText);
        }
        return false;
    }

    public boolean isAlertDisplayed() {
        List<WebElement> alerts = driver.findElements(alertBlockL);
        return alerts.size() > 0;
    }

    public List<String> getAlertMessageList() {
        List<String> errorMessages = new ArrayList<>();
        if(isAlertDisplayed()) {
            WebElement alertBlock = driver.findElement(alertBlockL);
            List<WebElement> alertErrorItems = alertBlock.findElements(alertErrorItemsL);
            for(WebElement errorItem: alertErrorItems) {
                String errorItemString = errorItem.getText();
                errorMessages.add(errorItemString);
            }
        }
        return errorMessages;
    }

    public List<String> doesAlertContainAll(String... expectedErrorArray) {
        List<String> expectedErrorMessages = Arrays.asList(expectedErrorArray);
        List<String> actualErrorMessages = getAlertMessageList();
        List<String> error404 = new ArrayList<>();

        if(getAlertMessageList().size() == expectedErrorMessages.size()) {
            for(String errorMessage: actualErrorMessages) {
                if(!expectedErrorMessages.contains(errorMessage)) {
                    error404.add(errorMessage);
                }
            }
        }
        return error404;
    }

    protected boolean isInputValid(By locator) {
        WebElement filed = driver.findElement(locator);
        if("input".equals(filed.getTagName())) {
            String colorValue = filed.getCssValue("color");
            return colorValue.equals(OK_COLOR);
        }
        return false;
    }

    public boolean isSuccessDisplayed() {
        List<WebElement> alerts = driver.findElements(alertSuccessL);
        return alerts.size() > 0;
    }

    public void selectSubCategory(String subcategory) {
        WebElement targetCategory = null;
        WebElement targetSubcategory = null;

        List<WebElement> categories = driver.findElements(navigationBarCategoryL);
        String lowerCaseedSubcategory = subcategory.toLowerCase();
        for(WebElement categoryItem: categories) {
            List<WebElement> subcategoryLinks = categoryItem
                    .findElements(By.cssSelector("ul > li > ul > li > a"));
            for(WebElement subcategoryLink: subcategoryLinks) {
                String subcategoryTitle = subcategoryLink.getAttribute("title").toLowerCase();
                if(subcategoryTitle.equals(lowerCaseedSubcategory)) {
                    targetCategory = categoryItem;
                    targetSubcategory = subcategoryLink;
                    break;
                }
            }
            if(targetCategory != null && targetSubcategory != null) {
                break;
            }
        }

        if(targetCategory != null && targetSubcategory != null) {
            Actions selectCategory = new Actions(driver);
            selectCategory
                    .moveToElement(targetCategory).build().perform();
            (new WebDriverWait(driver, TestUtils.MEDIUM_TIME_OUT))
                    .until(ExpectedConditions.visibilityOf(targetSubcategory));

            Actions selectSubcategory = new Actions(driver);
            selectSubcategory
                    .moveToElement(targetSubcategory)
                    .click().build().perform();
        }else {
            throw new IllegalStateException(
                    subcategory + " has not been found on the navigation!");
        }
    }

    public boolean isCartEmpty() {
        return TestUtils.await(driver, emptyCartIndicatorL).isDisplayed();
    }

    public int getNumberOfItemsInCart() {
        WebElement quantityIndicator = TestUtils.await(driver, numberOfItemsInCartL);
        return Integer.parseInt(quantityIndicator.getText());
    }

    public void toCartSummaryPage() {
        TestUtils.await(driver, cartLinkL).click();
    }

    public boolean isPageTitle(String title) {
        return driver.getTitle().equals(title);
    }
}
