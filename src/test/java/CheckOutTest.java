import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class CheckOutTest {

//    private WebDriver driver;
//
//    private String email;
//    private String password;
//    private String firstName;
//    private String lastName;
//
//    @BeforeTest
//    public void init() {
//        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver-90");
//        driver = new ChromeDriver();
//
//        email = "dummy@test.em";
//        password = "Dummy_P@$$W047";
//        firstName = "Test";
//        lastName = "User";
//    }
//
//    @Test
//    public void addToCartTest() {
//        driver.get("http://automationpractice.com/index.php");
//        driver.findElement(By.cssSelector("div.header_user_info > a.login")).click();
//
//        driver.findElement(By.id("email")).sendKeys(email);
//
//        driver.findElement(By.id("passwd")).sendKeys(password);
//
//        driver.findElement(By.name("SubmitLogin")).click();
//
//        String beginningOfWelcomeMessage = "Welcome to your account.";
//        WebElement welcomeP = (new WebDriverWait(driver, 10))
//                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("p.info-account")));
//        Assert.assertTrue(welcomeP.getText().contains(beginningOfWelcomeMessage));
//
//        WebElement navigationMenu = (new WebDriverWait(driver, 10))
//                .until(ExpectedConditions.presenceOfElementLocated(By.id("block_top_menu")));
//        WebElement category = navigationMenu.findElement(By.linkText("DRESSES"));
//        category.click();
//
//        String categoryNameString = (new WebDriverWait(driver, 10))
//                .until(ExpectedConditions.presenceOfElementLocated(
//                        By.cssSelector("span.category-name"))).getText();
//        Assert.assertTrue(categoryNameString.contains("Dresses"));
//
//        WebElement searchResultContext = (new WebDriverWait(driver, 10))
//                .until(ExpectedConditions.presenceOfElementLocated(By.id("center_column")));
//        List<WebElement> filteredItems = searchResultContext
//                .findElements(By.cssSelector("ul.product_list.grid.row > li"));
//        filteredItems.get(0).findElement(By.className("product-name")).click();
//
//        WebElement addToCartButton = (new WebDriverWait(driver, 10))
//                .until(ExpectedConditions.presenceOfElementLocated(By.id("add_to_cart")));
//        addToCartButton.click();
//
//        WebElement continueShoppingButton = (new WebDriverWait(driver, 10))
//                .until(ExpectedConditions
//                        .presenceOfElementLocated(By.cssSelector("#layer_cart > div.clearfix > div.layer_cart_cart.col-xs-12.col-md-6 > div.button-container > span")));
//        (new WebDriverWait(driver, 10))
//                .until(ExpectedConditions
//                        .visibilityOf(continueShoppingButton));
//        continueShoppingButton.click();
//    }
//
//    @AfterTest
//    public void tearDown() {
//        System.out.println("Shutting down tests");
//        driver.quit();
//    }
}
