package vip.poryi.base.exception;

public enum SystemError implements IError {
    SYSTEM_INTERNAL_ERROR("001", "服务器内部错误"),
    INVALID_PARAMETER("002", "参数错误"),
    CALL_REMOTE_ERROR("003", "微服务调用错误"),
    CALL_REMOTE_TIMEOUT_ERROR("004", "微服务调用超时"),
    NOT_SUPPORT("005", "不支持的请求"),
    ;

    String errorCode;
    String errorMsg;

    SystemError(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public String getNamespace() {
        return "";
    }

    @Override
    public String getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getErrorMessage() {
        return this.errorMsg;
    }
}
