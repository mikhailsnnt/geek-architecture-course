package ru.geekbrains.handler;

import ru.geekbrains.http.HttpResponseWriter;
import ru.geekbrains.http.common.HttpMethod;
import ru.geekbrains.http.common.HttpRequestPojo;
import ru.geekbrains.http.common.HttpResponsePojo;
import ru.geekbrains.view.ViewResolver;

@Handler(methods = HttpMethod.GET)
class ViewResolveRequestHandler extends HttpRequestHandler {
    private final ViewResolver viewResolver;

    public ViewResolveRequestHandler(ViewResolver viewResolver) {
        this.viewResolver = viewResolver;
    }

    @Override
    protected void handleInternal(HttpRequestPojo requestPojo, HttpResponseWriter responseWriter) {
        responseWriter.sendResponse(
                viewResolver.resolveViewByName(requestPojo.getUrl()).map(HttpResponsePojo::okOf)
                        .orElse(viewResolver.resolveViewByName("not_found.html").map(HttpResponsePojo::notFoundOf).orElseThrow())
        );
    }
}
