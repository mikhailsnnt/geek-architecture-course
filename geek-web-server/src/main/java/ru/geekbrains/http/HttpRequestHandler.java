package ru.geekbrains.http;

@FunctionalInterface
public interface HttpRequestHandler {
    HttpResponsePojo requestReceived(String url);
}
