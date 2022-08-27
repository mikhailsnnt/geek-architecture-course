package ru.geekbrains.http;

import ru.geekbrains.session.Session;

import java.net.Socket;

public class HttpSession extends Session{

    public HttpSession(Socket socket, HttpRequestHandler requestHandler) {
        super(startHttpSession(socket, requestHandler));
    }

    private static Runnable startHttpSession(Socket socket, HttpRequestHandler requestHandler) {
        return () -> {
            try (
                    HttpRequestReader requestReader = new HttpRequestReader(socket);
                    HttpResponseWriter responseWriter = new HttpResponseWriter(socket)
            ) {
                String requestUrl = requestReader.readGetRequestUrl();
                responseWriter.sendResponse(requestHandler.requestReceived(requestUrl));
            }
        };
    }
}
