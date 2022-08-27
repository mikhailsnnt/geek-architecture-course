package ru.geekbrains.http;

public class HttpResponsePojo {
    private static final String DEFAULT_PROTOCOL = "HTTP/1.1";
    private static final String DEFAULT_CHARSET = "utf-8";
    private static final String DEFAULT_CONTENT_TYPE = "text/html";
    private final String protocol;
    private final int code;
    private final String status;
    private final String contentType;
    private final String charset;
    private final String body;

    public HttpResponsePojo(int code, String body, String status) {
        this(code, status, DEFAULT_CONTENT_TYPE, body, DEFAULT_PROTOCOL, DEFAULT_CHARSET);
    }

    public HttpResponsePojo(int code, String status, String contentType, String body) {
        this(code, status, contentType, body, DEFAULT_PROTOCOL, DEFAULT_CHARSET);
    }


    public HttpResponsePojo(int code, String status, String contentType, String body, String protocol, String charset) {
        this.code = code;
        this.status = status;
        this.contentType = contentType;
        this.body = body;
        this.protocol = protocol;
        this.charset = charset;
    }

    public static HttpResponsePojo notFoundOf(String body) {
        return new HttpResponsePojo(404, body, "NOT_FOUND");
    }

    public static HttpResponsePojo okOf(String body) {
        return new HttpResponsePojo(200, body, "OK");
    }

    public String getProtocol() {
        return protocol;
    }

    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public String getContentType() {
        return contentType;
    }

    public String getCharset() {
        return charset;
    }

    public String getBody() {
        return body;
    }
}
