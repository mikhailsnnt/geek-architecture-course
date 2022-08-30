package ru.geekbrains.http.common;

import lombok.Builder;
import lombok.Data;

import static ru.geekbrains.http.common.HttpCommons.*;


@Data
@Builder
public class HttpResponsePojo {
    @Builder.Default
    private String protocol = DEFAULT_PROTOCOL;
    private int code;
    private String status;
    @Builder.Default
    private String contentType = DEFAULT_CONTENT_TYPE;
    @Builder.Default
    private String charset = DEFAULT_CHARSET;
    private String body;


    public static HttpResponsePojo notFoundOf(String body) {
        return builder()
                .code(404)
                .body(body)
                .status("NOT_FOUND")
                .build();
    }

    public static HttpResponsePojo okOf(String body) {
        return builder()
                .code(200)
                .status("OK")
                .body(body)
                .build();
    }
}
