import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SignUpTest {

//    @BeforeTest
//    public void init() {
//        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver-90");
//    }
//
//    @Test
//    public void signUpTest() {
//        WebDriver driver = new ChromeDriver();
//
//        UUID uuid = UUID.randomUUID();
//        String email = "dummy" + uuid + "@test.em";
//        String password = "Dummy_P@$$W047";
//        String firstName = "Test";
//        String lastName = "User";
//        String address = "D.Address, 00, Home";
//        String city = "Dummytown";
//        String zipCode = "00000";
//        String state = "Colorado";
//        String mobilePhone = "000000000";
//
//        driver.get("http://automationpractice.com/index.php");
//        driver.findElement(By.cssSelector("div.header_user_info > a.login")).click();
//
//        WebElement registerButton = (new WebDriverWait(driver, 10))
//                .until(ExpectedConditions.presenceOfElementLocated(By.id("SubmitCreate")));
//        driver.findElement(By.id("email_create")).sendKeys(email);
//        registerButton.click();
//
//        WebElement firstNameInput = (new WebDriverWait(driver, 10))
//                .until(ExpectedConditions.presenceOfElementLocated(By.id("customer_firstname")));
//        firstNameInput.sendKeys(firstName);
//
//        driver.findElement(By.id("customer_lastname")).sendKeys(lastName);
//
//        String emailValue = driver.findElement(By.id("email")).getAttribute("value");
//        Assert.assertEquals(email, emailValue);
//
//        driver.findElement(By.id("passwd")).sendKeys(password);
//        String colorValue = driver.findElement(By.id("passwd")).getCssValue("color");
//        Assert.assertEquals("rgba(156, 155, 155, 1)", colorValue);
//
//        new Select(driver.findElement(By.id("days"))).selectByValue("12");
//        new Select(driver.findElement(By.id("months"))).selectByValue("11");
//        new Select(driver.findElement(By.id("years"))).selectByValue("1977");
//
//        driver.findElement(By.id("firstname")).sendKeys(firstName);
//
//        driver.findElement(By.id("lastname")).sendKeys(lastName);
//
//        driver.findElement(By.id("address1")).sendKeys(address);
//
//        driver.findElement(By.id("city")).sendKeys(city);
//
//        new Select(driver.findElement(By.id("id_state"))).selectByVisibleText(state);
//
//        driver.findElement(By.id("postcode")).sendKeys(zipCode);
//
//        driver.findElement(By.id("phone_mobile")).sendKeys(mobilePhone);
//
//        driver.findElement(By.name("submitAccount")).click();
//
//        String beginningOfWelcomeMessage = "Welcome to your account.";
//        WebElement welcomeP = (new WebDriverWait(driver, 10))
//                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("p.info-account")));
//        Assert.assertTrue(welcomeP.getText().contains(beginningOfWelcomeMessage));
//
//        driver.findElement(By.cssSelector("div.header_user_info > a.logout")).click();
//
//        driver.quit();
//    }
//
//    @Test
//    public void logInTest() {
//        String email = "dummy@test.em";
//        String password = "Dummy_P@$$W047";
//        String firstName = "Test";
//        String lastName = "User";
//
//        WebDriver driver = new ChromeDriver();
//        driver.get("http://automationpractice.com/index.php");
//
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
//        WebElement accountLink = driver.findElement(By.cssSelector("div.header_user_info > a.account"));
//        String userLabel = accountLink.getText();
//        Assert.assertEquals(firstName + " " + lastName, userLabel);
//
//        driver.findElement(By.cssSelector("div.header_user_info > a.logout")).click();
//
//        driver.quit();
//    }
//
//    @Test
//    public void logInFailTest() {
//        WebDriver driver = new ChromeDriver();
//        driver.get("http://automationpractice.com/index.php");
//
//        driver.findElement(By.cssSelector("div.header_user_info > a.login")).click();
//
//        driver.findElement(By.id("email")).sendKeys("unacceptable@email.com");
//
//        driver.findElement(By.id("passwd")).sendKeys("password");
//
//        driver.findElement(By.name("SubmitLogin")).click();
//
//        String alertMessage = "There is 1 error";
//        List<String> listOfErrors = new ArrayList<String>();
//        listOfErrors.add("Authentication failed.");
//
//        WebElement alertBlock = (new WebDriverWait(driver, 10))
//                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.alert.alert-danger")));
//        String alertHeaderString = alertBlock.findElement(By.tagName("p")).getText();
//        Assert.assertTrue(alertHeaderString.contains("There is 1 error"));
//
//        List<WebElement> alertErrorItems = alertBlock.findElements(By.cssSelector("ol > li"));
//        Assert.assertEquals(listOfErrors.size(), alertErrorItems.size());
//        for(int i = 0; i < alertErrorItems.size(); i++) {
//            String errorItemString = alertErrorItems.get(i).getText();
//            Assert.assertTrue(errorItemString.contains(listOfErrors.get(i)));
//        }
//        driver.quit();
//    }
//
//    @AfterTest
//    public void tearDown() {
//        System.out.println("Shutting down tests");
//    }
}
