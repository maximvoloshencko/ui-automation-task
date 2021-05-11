package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.TestUtils;

public class RegistrationPage extends BasePage {
    private final By persInfoFirstNameL = By.id("customer_firstname");
    private final By persInfoLastNameL = By.id("customer_lastname");
    private final By emailInputL = By.id("email");
    private final By passwordInputL = By.id("passwd");
    private final By daysSelectL = By.id("days");
    private final By monthsSelectL = By.id("months");
    private final By yearsSelectL = By.id("years");
    private final By addressInputL = By.id("address1");
    private final By cityInputL = By.id("city");
    private final By stateSelectL = By.id("id_state");
    private final By postcodeInputL = By.id("postcode");
    private final By phoneMobileInputL = By.id("phone_mobile");
    private final By submitRegistrationButtonL = By.name("submitAccount");

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public void enterPersonalInformation(
            String firstName,
            String lastName,
            String email,
            String password,
            String day,
            String month,
            String year)
    {
        WebElement firstNameInput = (new WebDriverWait(driver, TestUtils.MEDIUM_TIME_OUT))
                .until(ExpectedConditions.presenceOfElementLocated(persInfoFirstNameL));
        firstNameInput.sendKeys(firstName);

        driver.findElement(persInfoLastNameL).sendKeys(lastName);

        String emailValue = driver.findElement(emailInputL)
                .getAttribute("value");

        driver.findElement(passwordInputL).sendKeys(password);
        String colorValue = driver.findElement(passwordInputL)
                .getCssValue("color");

        new Select(driver.findElement(daysSelectL)).selectByValue(day);
        new Select(driver.findElement(monthsSelectL)).selectByValue(month);
        new Select(driver.findElement(yearsSelectL)).selectByValue(year);
    }

    public void enterAddress(
            String address,
            String city,
            String state,
            String zipCode,
            String mobilePhone
    ) {
        driver.findElement(addressInputL).sendKeys(address);
        driver.findElement(cityInputL).sendKeys(city);
        new Select(driver.findElement(stateSelectL)).selectByVisibleText(state);
        driver.findElement(postcodeInputL).sendKeys(zipCode);
        driver.findElement(phoneMobileInputL).sendKeys(mobilePhone);
    }

    public void submit() {
        WebElement submtiRegistrationButton = (new WebDriverWait(driver, TestUtils.MEDIUM_TIME_OUT))
                .until(ExpectedConditions.presenceOfElementLocated(submitRegistrationButtonL));
        Actions reachSubmitButton = new Actions(driver);
        reachSubmitButton.moveToElement(submtiRegistrationButton)
                .click().build().perform();
    }
}
