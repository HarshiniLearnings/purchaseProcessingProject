package org.example.purchaseprocessingproject.exception;

public class CurrencyConversionException extends RuntimeException{

    public CurrencyConversionException(){
        super("Unable to find the rate within the last 6 months");
    }

}
