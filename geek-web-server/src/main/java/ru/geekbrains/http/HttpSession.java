package ru.geekbrains.http;

import java.net.Socket;

public class HttpSession {
    public HttpSession(Socket socket, HttpRequestHandler requestHandler) {
        startHttpSession(socket, requestHandler);
    }

    private void startHttpSession(Socket socket, HttpRequestHandler requestHandler) {
        new Thread(() -> {
            try (
                    HttpRequestReader requestReader = new HttpRequestReader(socket);
                    HttpResponseWriter responseWriter = new HttpResponseWriter(socket)
            ) {
                String requestUrl = requestReader.readGetRequestUrl();
                responseWriter.sendResponse(requestHandler.requestReceived(requestUrl));
            }
        }).start();
    }
}
