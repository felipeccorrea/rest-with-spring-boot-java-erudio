package br.com.erudio.services;

import br.com.erudio.exception.UnsupportedMathOperationException;
import org.springframework.stereotype.Component;

@Component
public class NumberConverter {

    public static Double convert(String value) {
        if (!isNumeric(value)) {
            throw new UnsupportedMathOperationException("Please enter a numeric value");
        }
        return Double.parseDouble(value.replace(",", "."));
    }

    private static boolean isNumeric(String value) {
        if (value == null) {
            return false;
        }
        String number = value.replace(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
