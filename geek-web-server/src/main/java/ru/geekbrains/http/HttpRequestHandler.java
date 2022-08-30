package ru.geekbrains.http;

import ru.geekbrains.http.common.HttpResponsePojo;

@FunctionalInterface
public interface HttpRequestHandler {
    HttpResponsePojo requestReceived(String url);
}
