package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.TestUtils;

public class AccountPage extends BasePage {
    private final By orderHistoryAndDetailsL = By.cssSelector("a[title=\"Orders\"]");
    private final By myCreditSlipsL = By.cssSelector("a[title=\"Credit slips\"]");
    private final By myAddressL = By.cssSelector("a[title=\"Addresses\"]");
    private final By myPersonalInformationL = By.cssSelector("a[title=\"Information\"]");

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public void toPersonalInformation() {
        if(isLoggedIn()) {
            TestUtils.await(driver, myPersonalInformationL).click();
        }
    }
}
