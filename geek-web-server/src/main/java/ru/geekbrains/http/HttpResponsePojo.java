package ru.geekbrains.http;

public class HttpResponsePojo {
    private static final String DEFAULT_PROTOCOL = "HTTP/1.1";
    private static final String DEFAULT_CHARSET = "utf-8";
    private static final String DEFAULT_CONTENT_TYPE = "text/html";
    private String protocol = DEFAULT_PROTOCOL;
    private int code;
    private String status;
    private String contentType = DEFAULT_CONTENT_TYPE;
    private String charset = DEFAULT_CHARSET;
    private String body;


    public static HttpResponsePojo notFoundOf(String body) {
        return newBuilder()
                .withStatusCode(404)
                .withBody(body)
                .withStatus("NOT_FOUND")
                .build();
    }

    public static HttpResponsePojo okOf(String body) {
        return newBuilder()
                .withStatusCode(200)
                .withStatus("OK")
                .withBody(body)
                .build();
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

    public static Builder newBuilder(){
        return new Builder();
    }

    static class Builder{
        private final HttpResponsePojo result;
        private Builder(){
            result = new HttpResponsePojo();
        }

        public Builder withProtocol(String protocol){
            result.protocol = protocol;
            return this;
        }

        public Builder withStatusCode(int code){
            result.code = code;
            return this;
        }

        public Builder withStatus(String status){
            result.status = status;
            return this;
        }

        public Builder withContentType(String contentType){
            result.contentType = contentType;
            return this;
        }

        public Builder withCharset(String charset){
            result.charset = charset;
            return this;
        }
        public Builder withBody(String body){
            result.body = body;
            return this;
        }

        public HttpResponsePojo build(){
            return result;
        }
    }

}
