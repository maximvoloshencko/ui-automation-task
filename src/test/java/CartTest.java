import models.ProductModel;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;
import prop.TestProps;
import utils.AvailableFilterOptions;

import java.util.ArrayList;
import java.util.List;

public class CartTest {

    private WebDriver driver;

    private HomePage homePage;
    private SignInPage signInPage;
    private AccountPage accountPage;
    private CatalogPage catalogPage;
    private CartSummaryPage cartSummaryPage;
    private ProductPage productPage;

    private final String email = TestProps.TEST_EMAIL;
    private final String password = TestProps.TEST_PASSWORD;
    private final String subcategory = "DRESSES";

    @BeforeTest
    public void init() {
        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver-90");
        driver = new ChromeDriver();
        driver.get(TestProps.APPLICATION_URL);

        homePage = new HomePage(driver);
        signInPage = new SignInPage(driver);
        accountPage = new AccountPage(driver);
        catalogPage = new CatalogPage(driver);
        cartSummaryPage = new CartSummaryPage(driver);
        productPage = new ProductPage(driver);
    }

    public int calculateTotalPriceWithoutTaxAndShipping(List<ProductModel> items) {
        int totalPrice = 0;
        for(ProductModel item: items) {
            totalPrice += item.getNumericPrice();
        }
        return totalPrice;
    }

    public boolean doPricesWithoutTaxMatchWith(List<ProductModel> items) {
        int expectedTotalPrice = calculateTotalPriceWithoutTaxAndShipping(items);
        int actualTotalPrice =
                cartSummaryPage.getTotalPrice() -
                        cartSummaryPage.getTax();
        return expectedTotalPrice == actualTotalPrice;
    }

    @Test
    public void test() {

        homePage.toSignInPage();
        signInPage.logIn(email, password);

        Assert.assertTrue(
                accountPage.isLoggedIn(),
                "Logging in did not happen!");

        signInPage.selectSubCategory(subcategory);

        catalogPage.filterSize(
                AvailableFilterOptions.Size.L);
        catalogPage.filterColor(
                AvailableFilterOptions.Color.Yellow);
        catalogPage.filterComposition(
                AvailableFilterOptions.Composition.Viscose);
        catalogPage.filterStyle(
                AvailableFilterOptions.Style.Girly);

//        if(catalogPage.isLoadingIndicatorOn()) {
//            try {
//                catalogPage.waitWhileLoading();
//            }catch (TimeoutException ex) {
//                Assert.fail("Loading of new products after applying filters has timed out!");
//            }
//        }

        if(!catalogPage.isCartEmpty()) {
            catalogPage.toCartSummaryPage();
            cartSummaryPage.deleteAllItems();
            cartSummaryPage.back();
        }

        List<ProductModel> itemsAddedToCart = new ArrayList<>();

        for(int i = 0; i < catalogPage.getAmountOfItemsOnPage(); i++) {
            ProductModel item = catalogPage.getItem(i);
            if(item.isAvailable()) {
                catalogPage.addToCart(i);
                itemsAddedToCart.add(item);
                catalogPage.addedToCartModal().continueShopping();
            }
        }

        Assert.assertEquals(
                catalogPage.getNumberOfItemsInCart(),
                itemsAddedToCart.size(),
                "Number of added items does not match number of items in cart!");

        Assert.assertEquals(
                cartSummaryPage.getNumberOfItemsInCart(),
                itemsAddedToCart.size(),
                "Number of items is cart does not match!");

        catalogPage.toCartSummaryPage();

        Assert.assertTrue(doPricesWithoutTaxMatchWith(itemsAddedToCart),
                "Total price of all items in cart does not match!");

        int itemToDelete = 0;
        cartSummaryPage.deleteItem(itemToDelete);
        itemsAddedToCart.remove(itemToDelete);

        Assert.assertEquals(cartSummaryPage.getNumberOfItems(),
                itemsAddedToCart.size(),
                "Number of items in cart does not match after removal!");

        Assert.assertTrue(doPricesWithoutTaxMatchWith(itemsAddedToCart),
                "Total price of all items in cart does not match!");

        cartSummaryPage.deleteAllItems();

        Assert.assertTrue(cartSummaryPage.isCartEmpty(),
                "Number of items in cart does not match after removal!");
        Assert.assertTrue(cartSummaryPage.isCartEmptyWarningPresent(),
                "Empty cart warning message is not present!");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
