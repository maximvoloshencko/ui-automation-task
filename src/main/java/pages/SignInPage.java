package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.TestUtils;

public class SignInPage extends BasePage {
    private final By emailInputL = By.id("email");
    private final By passwordInputL = By.id("passwd");
    private final By submitLoginButtonL = By.name("SubmitLogin");
    private final By emailForRegistrationL = By.id("email_create");
    private final By submitEmailButtonL = By.id("SubmitCreate");

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    public void logIn(String email, String password) {
        TestUtils.await(driver, emailInputL).clear();
        driver.findElement(emailInputL).sendKeys(email);
        TestUtils.await(driver, passwordInputL).clear();
        driver.findElement(passwordInputL).sendKeys(password);
        driver.findElement(submitLoginButtonL).click();
    }

    public void initRegistration(String email) {
        WebElement registerButton = (new WebDriverWait(driver, TestUtils.MEDIUM_TIME_OUT))
                .until(ExpectedConditions.presenceOfElementLocated(submitEmailButtonL));
        driver.findElement(emailForRegistrationL).sendKeys(email);
        registerButton.click();
    }
}
