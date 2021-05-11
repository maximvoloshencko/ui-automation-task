package pages;

import java.util.Currency;

public interface PriceParser {

    default int parsePrice(String price, Currency currency) {
        String currencySymbol = currency.getSymbol();
        float fPrice = Float.parseFloat(
                price.replace(currencySymbol, ""));
        return (int)(fPrice * 100);
    }

    default int parsePrice(String price) {
        return parsePrice(price, Currency.getInstance("USD"));
    }
}
