package ru.geekbrains.handler;

import ru.geekbrains.http.HttpResponseWriter;
import ru.geekbrains.http.common.HttpMethod;
import ru.geekbrains.http.common.HttpRequestPojo;

import java.util.Set;

abstract class HttpRequestHandler implements RequestHandler {
    private RequestHandler nextHandler;

    private Set<HttpMethod> supportedMethods;

    @Override
    public void handleRequest(HttpRequestPojo request, HttpResponseWriter responseWriter) {
        if (supportedMethods.contains(request.getMethod()) || supportedMethods.contains(HttpMethod.ANY))
            handleInternal(request,responseWriter);
        else {
            if(nextHandler != null)
                nextHandler.handleRequest(request,responseWriter);
            else
                throw new IllegalStateException(String.format("Could not process request: %s", request));
        }
    }

    protected abstract void handleInternal(HttpRequestPojo requestPojo, HttpResponseWriter responseWriter);
}
