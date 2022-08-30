package ru.geekbrains.http.reader;

import ru.geekbrains.http.HttpException;
import ru.geekbrains.http.common.HttpRequestPojo;


public interface HttpRequestReader extends AutoCloseable {
    HttpRequestPojo readRequest() throws HttpException;
}
