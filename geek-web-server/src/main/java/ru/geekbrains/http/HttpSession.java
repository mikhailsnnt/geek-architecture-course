package ru.geekbrains.http;

import ru.geekbrains.handler.RequestHandler;
import ru.geekbrains.http.reader.HttpBufferedRequestReader;
import ru.geekbrains.http.reader.HttpLoggingRequestReader;
import ru.geekbrains.http.reader.HttpRequestReader;
import ru.geekbrains.session.Session;

import java.net.Socket;

public class HttpSession extends Session{

    public HttpSession(Socket socket, RequestHandler requestHandler) {
        super(startHttpSession(socket, requestHandler));
    }

    private static Runnable startHttpSession(Socket socket, RequestHandler requestHandler) {
        return () -> {
            try (
                    HttpRequestReader requestReader = new HttpLoggingRequestReader(new HttpBufferedRequestReader(socket));
                    HttpResponseWriter responseWriter = new HttpResponseWriter(socket)
            ) {
                requestHandler.handleRequest(requestReader.readRequest(),responseWriter);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}
