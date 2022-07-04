package cn.asilentboy.blog.utils.http;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ECode {

    SUCCESS(200, "OK"),
    FORBIDDEN(403, "权限不足"),
    UNAUTHORIZED(405, "认证失败"),
    FAIL(500, "FAIL"),

    POST_NOT_FOUND(5001, "文章不存在");

    final int code;
    final String message;
}
