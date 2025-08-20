package com.hdfc.minibank.exceptions;

public class InvalidDepositValueException extends RuntimeException {
    public InvalidDepositValueException(String message) {
        super(message);
    }
}
