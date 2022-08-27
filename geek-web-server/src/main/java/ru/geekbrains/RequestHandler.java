package ru.geekbrains;

import ru.geekbrains.http.HttpRequestHandler;
import ru.geekbrains.http.HttpResponsePojo;
import ru.geekbrains.view.ViewResolver;


public class RequestHandler implements HttpRequestHandler {

    private final ViewResolver viewResolver;


    public RequestHandler(ViewResolver viewResolver) {
        this.viewResolver = viewResolver;
    }

    @Override
    public HttpResponsePojo requestReceived(String url) {
        return resolveView(url);
    }

    private HttpResponsePojo resolveView(String name) {
        return viewResolver.resolveViewByName(name).map(HttpResponsePojo::okOf)
                .orElse(viewResolver.resolveViewByName("not_found.html").map(HttpResponsePojo::notFoundOf).orElseThrow());
    }
}
