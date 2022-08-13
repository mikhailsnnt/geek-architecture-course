package ru.geekbrains.http;

public class HttpException extends RuntimeException{
    public HttpException(Throwable cause) {
        super(cause);
    }
}
