package vip.poryi.base.exception;

public interface IError {
    String getNamespace();

    String getErrorCode();

    String getErrorMessage();
}
