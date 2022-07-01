package cn.asilentboy.blog.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response {
    private Integer code;
    private String message;
    private Object data;

    public Response(Integer code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public static Response makeSuccess(Object data) {
        return new Response(ECode.SUCCESS.code, ECode.SUCCESS.message, data);
    }

    public static Response makeError(Object data) {
        return new Response(ECode.FAIL.code, ECode.FAIL.message, data);
    }
}
