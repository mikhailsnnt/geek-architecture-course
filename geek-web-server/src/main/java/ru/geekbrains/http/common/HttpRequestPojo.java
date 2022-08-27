package ru.geekbrains.http.common;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.HashMap;
import java.util.Map;

import static ru.geekbrains.http.common.HttpCommons.*;

@Data
@Builder
public class HttpRequestPojo {
    private HttpMethod method;
    private String url;
    @Builder.Default
    private String protocol = DEFAULT_PROTOCOL;
    @Singular
    private Map<String, String> headers;
    private String body;
}
