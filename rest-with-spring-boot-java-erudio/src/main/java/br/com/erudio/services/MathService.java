package br.com.erudio.services;

import br.com.erudio.exception.UnsupportedMathOperationException;
import org.springframework.stereotype.Service;

import java.util.function.DoubleBinaryOperator;

@Service
public class MathService {

    public Double sum(String numberOne, String numberTwo) {
        return calculate(numberOne, numberTwo, (a, b) -> a + b);
    }

    public Double subtraction(String numberOne, String numberTwo) {
        return calculate(numberOne, numberTwo, (a, b) -> a - b);
    }

    public Double multiplication(String numberOne, String numberTwo) {
        return calculate(numberOne, numberTwo, (a, b) -> a * b);
    }

    public Double division(String numberOne, String numberTwo) {
        return calculate(numberOne, numberTwo, (a, b) -> {
            if (b == 0) {
                throw new UnsupportedMathOperationException("Division by zero is not allowed");
            }
            return a / b;
        });
    }

    public Double squareRoot(String number) {
        double value = parseNumber(number);
        if (value < 0) {
            throw new UnsupportedMathOperationException("Square root of a negative number is not supported");
        }
        return Math.sqrt(value);
    }

    private Double calculate(String numberOne, String numberTwo, DoubleBinaryOperator operation) {
        return operation.applyAsDouble(parseNumber(numberOne), parseNumber(numberTwo));
    }

    private Double parseNumber(String value) {
        if (!isNumeric(value)) {
            throw new UnsupportedMathOperationException("Please enter a numeric value");
        }
        return Double.parseDouble(value.replace(",", "."));
    }

    private boolean isNumeric(String value) {
        if (value == null) {
            return false;
        }
        String number = value.replace(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
