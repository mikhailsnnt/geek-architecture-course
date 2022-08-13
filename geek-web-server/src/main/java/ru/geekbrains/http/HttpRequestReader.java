package ru.geekbrains.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


public class HttpRequestReader implements AutoCloseable {
    private final BufferedReader input;

    public HttpRequestReader(Socket socket) throws HttpException {
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new HttpException(e);
        }
    }

    public String readGetRequestUrl() throws HttpException {
        try {
            //For simplicity case supporting only GET request
            while (!input.ready()) ;

            String firstLine = input.readLine();
            String[] parts = firstLine.split(" ");
            System.out.println(firstLine);
            if (!parts[0].equals("GET"))
                throw new IllegalStateException("Not GET method recieved");
            while (input.ready()) {
                System.out.println(input.readLine());
            }
            return parts[1];
        } catch (IOException e) {
            throw new HttpException(e);
        }
    }

    @Override
    public void close() throws HttpException {
        try {
            input.close();
        } catch (IOException e) {
            throw new HttpException(e);
        }
    }
}
