package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class PersonalInformationPage extends BasePage {
    private final By firstNameInputL = By.id("firstname");
    private final By lastNameInputL = By.id("lastname");
    private final By emailInputL = By.id("email");
    private final By daysSelectL = By.id("days");
    private final By monthsSelectL = By.id("months");
    private final By yearsSelectL = By.id("years");

    private final By currentPasswordL = By.id("old_passwd");
    private final By newPasswordL = By.id("passwd");
    private final By confirmPasswordL = By.id("confirmation");

    private final By saveButton = By.name("submitIdentity");

    public PersonalInformationPage(WebDriver driver) {
        super(driver);
    }

    public String getFirstNameValue() {
        return driver.findElement(firstNameInputL).getAttribute("value");
    }

    public void enterFirstName(String newValue) {
        driver.findElement(firstNameInputL).clear();
        driver.findElement(firstNameInputL).sendKeys(newValue);
    }

    public String getLastNameValue() {
        return driver.findElement(lastNameInputL).getAttribute("value");
    }

    public void enterLastName(String newValue) {
        driver.findElement(lastNameInputL).clear();
        driver.findElement(lastNameInputL).sendKeys(newValue);
    }

    public String getEmailValue() {
        return driver.findElement(emailInputL).getAttribute("value");
    }

    public void enterEmail(String newValue) {
        driver.findElement(emailInputL).clear();
        driver.findElement(emailInputL).sendKeys(newValue);
    }

    public String getDayValue() {
        return driver.findElement(daysSelectL).getAttribute("value");
    }

    public void selectDay(String newValue) {
        new Select(driver.findElement(daysSelectL)).selectByValue(newValue);
    }

    public String getMonthValue() {
        return driver.findElement(monthsSelectL).getAttribute("value");
    }

    public void selectMonth(String newValue) {
        new Select(driver.findElement(monthsSelectL)).selectByValue(newValue);
    }

    public String getYearValue() {
        return driver.findElement(yearsSelectL).getAttribute("value");
    }

    public void selectYear(String newValue) {
        new Select(driver.findElement(yearsSelectL)).selectByValue(newValue);
    }

    public void selectBirthDate(String dayValue, String monthValue, String yearValue) {
        selectDay(dayValue);
        selectMonth(monthValue);
        selectYear(yearValue);
    }

    public void enterNewPassword(
            String currentPasswordValue,
            String newPasswordValue,
            String confirmPasswordValue) {
        WebElement currentPasswordInput = driver.findElement(currentPasswordL);
        currentPasswordInput.clear();
        currentPasswordInput.sendKeys(currentPasswordValue);
        driver.findElement(newPasswordL).click();
        if(isInputValid(currentPasswordL)) {
            driver.findElement(newPasswordL).sendKeys(newPasswordValue);
            driver.findElement(confirmPasswordL).sendKeys(confirmPasswordValue);
        }
    }

    public void save() {
        driver.findElement(saveButton).click();
    }
}
