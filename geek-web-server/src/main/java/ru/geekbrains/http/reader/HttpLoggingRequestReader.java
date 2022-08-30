package ru.geekbrains.http.reader;

import ru.geekbrains.http.HttpException;
import ru.geekbrains.http.common.HttpRequestPojo;

public class HttpLoggingRequestReader implements  HttpRequestReader{
    private final HttpRequestReader requestReader;

    public HttpLoggingRequestReader(HttpRequestReader requestReader){
        this.requestReader = requestReader;
    }


    @Override
    public HttpRequestPojo readRequest() throws HttpException {
        HttpRequestPojo requestPojo = requestReader.readRequest();
        System.out.printf("Incoming request: %s\n", requestPojo);
        return requestPojo;
    }

    @Override
    public void close() throws Exception {
        requestReader.close();
    }
}
