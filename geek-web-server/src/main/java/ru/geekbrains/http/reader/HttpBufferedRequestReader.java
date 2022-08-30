package ru.geekbrains.http.reader;

import ru.geekbrains.http.HttpException;
import ru.geekbrains.http.common.HttpMethod;
import ru.geekbrains.http.common.HttpRequestPojo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


public class HttpBufferedRequestReader implements HttpRequestReader {
    private final BufferedReader input;

    public HttpBufferedRequestReader(Socket socket) throws HttpException {
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
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

    @Override
    public HttpRequestPojo readRequest() throws HttpException {
        try {
            while (!input.ready()) ;

            String firstLine = input.readLine();
            String[] parts = firstLine.split(" ");

            HttpRequestPojo.HttpRequestPojoBuilder requestBuilder = HttpRequestPojo.builder();
            requestBuilder.method(HttpMethod.valueOf(parts[0]));
            requestBuilder.url(parts[1]);
            requestBuilder.protocol(parts[2]);
            while (input.ready()) {
                String line = input.readLine();
                if(line.contains(":")) {
                    String[] split = line.split(":");
                    requestBuilder.header(split[0],split[1]);
                }
                else
                    break;
            }

            StringBuilder bodyBuilder = new StringBuilder();
            while(input.ready()){
                bodyBuilder.append(input.readLine());
            }

            return requestBuilder
                    .body(bodyBuilder.toString())
                    .build();
        } catch (IOException e) {
            throw new HttpException(e);
        }
    }
}
