package com.radnoti.webshop;

public class ValamilyenException extends Exception{
    public ValamilyenException(String message) {
        super(message);
    }
    protected ValamilyenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
