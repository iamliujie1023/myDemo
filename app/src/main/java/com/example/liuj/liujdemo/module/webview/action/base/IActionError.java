package com.example.liuj.liujdemo.module.webview.action.base;

public class IActionError {
    public int code;
    public String message;

    public static final int ERROR_CODE_INVALID_PARAM = 1;
    public static final int ERROR_CODE_FAILED = 2;
    public static final int ERROR_CODE_USER_DENIED = 3;
    public static final int ERROR_CODE_USER_DISABLED = 4;
    public static final int ERROR_CODE_FILE_NOT_EXIST = 5;
    public static final int ERROR_CODE_USER_NOT_LOGIN = 6;
    public static final int ERROR_CODE_OTHER = 99;

    public IActionError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static IActionError getInvalidParamError(String name) {
        return new IActionError(ERROR_CODE_INVALID_PARAM, "无效的参数: " + name);
    }

    public static IActionError getFailedError() {
        return new IActionError(ERROR_CODE_FAILED, "操作失败");
    }

    public static IActionError getUserDeniedError() {
        return new IActionError(ERROR_CODE_USER_DENIED, "用户拒绝");
    }

    public static IActionError getUserDisabledError() {
        return new IActionError(ERROR_CODE_USER_DISABLED, "功能已关闭");
    }

    public static IActionError getFileNotExistError() {
        return new IActionError(ERROR_CODE_FILE_NOT_EXIST, "文件不存在");
    }

    public static IActionError getUserNotLoginError() {
        return new IActionError(ERROR_CODE_USER_NOT_LOGIN, "用户未登录");
    }
}
