package cn.asilentboy.blog.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ECode {

    SUCCESS(200, "OK"),
    FAIL(500, "FAIL"),

    POST_NOT_FOUND(5001, "文章不存在");

    final int code;
    final String message;
}
