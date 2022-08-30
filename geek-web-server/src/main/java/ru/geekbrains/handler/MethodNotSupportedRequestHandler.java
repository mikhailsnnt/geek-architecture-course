package ru.geekbrains.handler;

import ru.geekbrains.http.HttpResponseWriter;
import ru.geekbrains.http.common.HttpMethod;
import ru.geekbrains.http.common.HttpRequestPojo;
import ru.geekbrains.http.common.HttpResponsePojo;

//Quick and dirty
@Handler(methods = HttpMethod.ANY, order = Integer.MAX_VALUE)
class MethodNotSupportedRequestHandler extends HttpRequestHandler{

    @Override
    protected void handleInternal(HttpRequestPojo requestPojo, HttpResponseWriter responseWriter) {
        responseWriter.sendResponse(
                HttpResponsePojo.builder()
                        .code(405)
                        .status("Method not allowed")
                        .body(
                                """
                                <h1>
                                    405 Method not allowed, buddy!
                                </h1>"""
                        )
                        .build()
        );
    }
}
