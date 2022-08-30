package ru.geekbrains.handler;

import ru.geekbrains.http.HttpResponseWriter;
import ru.geekbrains.http.common.HttpMethod;
import ru.geekbrains.http.common.HttpRequestPojo;
import ru.geekbrains.http.common.HttpResponsePojo;


@Handler(methods = {HttpMethod.POST, HttpMethod.PUT})
class PostRequestHandler extends HttpRequestHandler{
    public PostRequestHandler() {
    }

    @Override
    protected void handleInternal(HttpRequestPojo requestPojo, HttpResponseWriter responseWriter) {
        responseWriter.sendResponse(
                HttpResponsePojo.okOf(
                        """
                                <h1>
                                    PostRequestHandler handled your method, buddy
                                </h1>
                                """
                )
        );
    }
}
