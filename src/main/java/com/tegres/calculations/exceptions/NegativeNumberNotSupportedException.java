package com.tegres.calculations.exceptions;

import java.util.List;

public class NegativeNumberNotSupportedException extends RuntimeException {
    public List<Integer> negativeNumbers;

    public NegativeNumberNotSupportedException(List<Integer> negativeNumbers) {
        this.negativeNumbers = negativeNumbers;
    }

    public NegativeNumberNotSupportedException(String message) {
        super(message);
    }

    public NegativeNumberNotSupportedException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessage() {
        return "negatives not allowed ".concat(negativeNumbers.toString());
    }
}
