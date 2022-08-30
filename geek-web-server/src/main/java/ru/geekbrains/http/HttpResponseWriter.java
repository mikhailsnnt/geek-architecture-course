package ru.geekbrains.http;

import ru.geekbrains.http.common.HttpResponsePojo;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class HttpResponseWriter implements AutoCloseable {
    private final PrintWriter writer;

    public HttpResponseWriter(Socket socket) throws HttpException {
        try {
            writer = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            throw new HttpException(e);
        }
    }

    public void sendResponse(HttpResponsePojo response) throws RuntimeException {
        try {
            writer.println(String.format("%s %d %s", response.getProtocol(), response.getCode(), response.getStatus()));
            writer.println(String.format("Content-Type: %s; charset=%s", response.getContentType(), response.getCharset()));
            writer.println();
            writer.println(response.getBody());
            writer.flush();
        } catch (Exception e) {
            throw new HttpException(e);
        }
    }

    @Override
    public void close() throws HttpException {
        try {
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
