package com.lukomski.wojtek.CarRentalApp.exceptions;

public class RentTimeTooLongException extends RuntimeException{
    public RentTimeTooLongException(String message) {
        super(message);
    }
}
