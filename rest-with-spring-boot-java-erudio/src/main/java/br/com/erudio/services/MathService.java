package br.com.erudio.services;

import br.com.erudio.exception.UnsupportedMathOperationException;
import org.springframework.stereotype.Service;

import java.util.function.DoubleBinaryOperator;

@Service
public class MathService {

    private final NumberConverter numberConverter;

    public MathService(NumberConverter numberConverter) {
        this.numberConverter = numberConverter;
    }

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
        double value = numberConverter.convert(number);
        if (value < 0) {
            throw new UnsupportedMathOperationException("Square root of a negative number is not supported");
        }
        return Math.sqrt(value);
    }

    private Double calculate(String numberOne, String numberTwo, DoubleBinaryOperator operation) {
        return operation.applyAsDouble(numberConverter.convert(numberOne), numberConverter.convert(numberTwo));
    }
}
