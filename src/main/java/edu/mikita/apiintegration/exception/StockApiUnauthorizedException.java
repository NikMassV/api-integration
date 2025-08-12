package edu.mikita.apiintegration.exception;

public class StockApiUnauthorizedException extends RuntimeException {

    public StockApiUnauthorizedException(String message) {
        super(message);
    }
}
