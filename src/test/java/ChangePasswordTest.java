import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.*;
import prop.TestProps;
import utils.TestUtils;

import java.util.List;

public class ChangePasswordTest {
    private WebDriver driver;

    private HomePage homePage;
    private SignInPage signInPage;
    private RegistrationPage registrationPage;
    private AccountPage accountPage;
    private PersonalInformationPage personalInformationPage;

    private final String email = TestProps.generateDummyEmail();
    private final String password = TestProps.generateDummyPassword();
    private final String newPassword = TestProps.generateDummyPassword();

    @BeforeTest
    public void init() {
        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver-90");
        driver = new ChromeDriver();
        driver.get(TestProps.APPLICATION_URL);

        homePage = new HomePage(driver);
        signInPage = new SignInPage(driver);
        registrationPage = new RegistrationPage(driver);
        accountPage = new AccountPage(driver);
        personalInformationPage = new PersonalInformationPage(driver);
    }

    @Test
    public void test() {

        homePage.toSignInPage();

        signInPage.initRegistration(email);

        registrationPage.submit();
        List<String> notFoundErrorMessages = registrationPage.doesAlertContainAll(
                "You must register at least one phone number.",
                "The Zip/Postal code you've entered is invalid. It must follow this format: 00000",
                "lastname is required.",
                "firstname is required.",
                "passwd is required.",
                "This country requires you to choose a State.",
                "address1 is required.",
                "city is required.");
        if(notFoundErrorMessages.size() > 0) {
            Assert.fail(TestUtils.formatMessageWithList(
                    "Not found error messages:",
                    notFoundErrorMessages));
        }

        registrationPage.enterPersonalInformation(
                "New",
                "User",
                email,
                password,
                "1", "1", "2000"
        );
        registrationPage.enterAddress(
                "str Dummystreet, 99, Home",
                "Dummytown",
                "Colorado",
                "99999",
                "+99 999 9999"
        );

        registrationPage.submit();
        List<String> errorMessages = registrationPage.getAlertMessageList();
        if(errorMessages.size() > 0) {
            Assert.fail(TestUtils.formatMessageWithList(
                    "Registration failed!:", errorMessages));
        }

        registrationPage.signOut();
        registrationPage.toSignInPage();
        signInPage.logIn(email, password);
        Assert.assertTrue(
                accountPage.isLoggedIn(),
                "Logging in did not happen!");

        accountPage.toPersonalInformation();
        personalInformationPage.enterNewPassword(
                password,
                newPassword,
                newPassword);
        personalInformationPage.save();
        Assert.assertTrue(
                personalInformationPage.isSuccessDisplayed(),
                "Password has not been changegd successfully!");

        personalInformationPage.signOut();
        homePage.toSignInPage();
        signInPage.logIn(email, password);
        Assert.assertTrue(
                signInPage.isAlertDisplayed(),
                "Expected Error Alert did not appear!");

        signInPage.logIn(email, newPassword);
        Assert.assertFalse(
                signInPage.isAlertDisplayed(),
                "Logging in has been unsuccessful with new password!");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
