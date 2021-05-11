import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver-90");
        WebDriver driver = new ChromeDriver();
        driver.get("http://automationpractice.com/index.php");

        HomePage homePage = new HomePage(driver);
        homePage.toSignInPage();

        SignInPage signInPage = new SignInPage(driver);
        signInPage.logIn("dummy@test.em", "Dummy_P@$$W047");
        System.out.println("Is logged in? -> " + signInPage.isLoggedIn());
        System.out.println("Is logged in as Test User? -> " + signInPage.isLoggedInAs("Test", "User"));

        signInPage.signOut();
        System.out.println("Is logged in? -> " + signInPage.isLoggedIn());

        homePage.toSignInPage();

        UUID uuid = UUID.randomUUID();
//        String email = "dummy" + uuid + "@test.em";
        String email = "test@test.em";
//        String password = "Dummy_P@$$W047";
        String password = "qwerty123456";
        String firstName = "Tteesstt";
        String lastName = "User";
        String address = "D.Address, 00, Home";
        String city = "Dummytown";
        String zipCode = "00000";
        String state = "Colorado";
        String mobilePhone = "000000000";

        signInPage.initRegistration(email);
        System.out.println("Is alert displayed? -> " + signInPage.isAlertDisplayed());
        for(String error: signInPage.getAlertMessageList()) {
            System.out.println("..." + error);
        }
        RegistrationPage registrationPage = new RegistrationPage(driver);

        final List<String> registrationErrors = new ArrayList<String>();
        registrationErrors.add("You must register at least one phone number.");
        registrationErrors.add("The Zip/Postal code you've entered is invalid. It must follow this format: 00000");
        registrationErrors.add("lastname is required.");
        registrationErrors.add("firstname is required.");
        registrationErrors.add("passwd is required.");
        registrationErrors.add("This country requires you to choose a State.");
        registrationErrors.add("address1 is required.");
        registrationErrors.add("city is required.");

        registrationPage.submit();
        List<String> actualErrorMessages = registrationPage.getAlertMessageList();
        if(actualErrorMessages.size() == registrationErrors.size()) {
            System.out.println(actualErrorMessages.size() + ", " + registrationErrors.size());
            System.out.println("Amount of error messages matched!");
        }
        for(String errorMessage: actualErrorMessages) {
            if(registrationErrors.contains(errorMessage)) {
                System.out.println(errorMessage + " is present");
            }else {
                System.err.println(errorMessage + " NOT FOUND!");
            }
        }

        registrationPage.enterPersonalInformation(
                firstName,
                lastName,
                email,
                password,
                "4", "7", "1987"
        );
        registrationPage.enterAddress(
                address,
                city,
                state,
                zipCode,
                mobilePhone
        );
        registrationPage.submit();

        AccountPage accountPage = new AccountPage(driver);
        accountPage.toPersonalInformation();

        PersonalInformationPage personalInformationPage = new PersonalInformationPage(driver);
        System.out.println("..." + personalInformationPage.getFirstNameValue());
        System.out.println("..." + personalInformationPage.getLastNameValue());
        System.out.println("..." + personalInformationPage.getEmailValue());
        personalInformationPage.selectBirthDate("8", "3", "2001");
        System.out.println("..." + personalInformationPage.getDayValue());
        System.out.println("..." + personalInformationPage.getMonthValue());
        System.out.println("..." + personalInformationPage.getYearValue());
        personalInformationPage.enterNewPassword(
                password, "pass123", "pass123");
        personalInformationPage.save();
        System.out.println("..." + personalInformationPage.isSuccessDisplayed());

        driver.quit();
    }
}
