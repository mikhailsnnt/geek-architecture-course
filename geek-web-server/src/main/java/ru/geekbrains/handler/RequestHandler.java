package ru.geekbrains.handler;

import ru.geekbrains.http.HttpResponseWriter;
import ru.geekbrains.http.common.HttpRequestPojo;

public interface RequestHandler {
    void handleRequest(HttpRequestPojo request, HttpResponseWriter responseWriter);
}
