package ru.geekbrains.handler;

import ru.geekbrains.http.HttpResponseWriter;
import ru.geekbrains.http.common.HttpRequestPojo;

class LoggingRequestHandler implements RequestHandler {
    private RequestHandler nextHandler;

    @Override
    public void handleRequest(HttpRequestPojo request, HttpResponseWriter responseWriter) {
        System.out.println("Handling request "+request);
        if(nextHandler!=null)
            nextHandler.handleRequest(request,responseWriter);
    }
}
