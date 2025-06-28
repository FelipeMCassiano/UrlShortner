package com.felipemcassiano.UrlShortener.Exceptions;

public class NotFoundUrlException extends RuntimeException {
    public NotFoundUrlException(String message) {
        super(message);
    }
}
