package utils;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public abstract class AvailableFilterOptions {

    public enum Size {
        S("S"),
        M("M"),
        L("L");

        private final String optionValue;

        Size(String value) {
            optionValue = value;
        }

        public boolean is(String val) {
            return optionValue.equals(val.toUpperCase());
        }
    }

    public enum Color {
        Beige("Beige"),
        Black("Black"),
        Blue("Blue"),
        Yellow("Yellow"),
        White("White"),
        Orange("Orange"),
        Green("Green"),
        Pink("Pink");

        private final String optionValue;

        Color(String value) {
            optionValue = value;
        }

        public boolean is(String val) {
            return optionValue.toUpperCase()
                    .equals(val.toUpperCase());
        }

        public static Color from(String value) {
            String capitalLetter = value.substring(0, 1).toUpperCase();
            String restOfValue = value.substring(1);
            String formattedValue = capitalLetter + restOfValue;

            return Color.valueOf(formattedValue);
        }

        public static List<Color> from(List<String> values) {
            List<Color> colors = new ArrayList<>();
            for(String c: values) {
                colors.add(from(c));
            }
            return colors;
        }
    }

    public enum Composition {
        Cotton("Cotton"),
        Polyester("Polyester"),
        Viscose("Viscose");

        private final String optionValue;

        Composition(String value) {
            optionValue = value;
        }

        public boolean is(String val) {
            return optionValue.toUpperCase()
                    .equals(val.toUpperCase());
        }
    }

    public enum Style {
        Casual("Casual"),
        Dressy("Dressy"),
        Girly("Girly");

        private final String optionValue;

        Style(String value) {
            optionValue = value;
        }

        public boolean is(String val) {
            return optionValue.toUpperCase()
                    .equals(val.toUpperCase());
        }
    }
}
