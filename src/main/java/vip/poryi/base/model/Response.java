package vip.poryi.base.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import vip.poryi.base.exception.IError;
import vip.poryi.base.exception.SystemError;

import java.io.Serializable;

/**
 * 通用返回类型
 * @author pengchen
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> implements Serializable {

    public static final String CODE_SUCCESS = "000000";
    public static final String MSG_SUCCESS = "SUCCESS";
    /**
     * 每一次接口返回类型的json格式统一为
     * code:XXX
     * msg:XXX
     * data:XXX
     */
    private String code;
    private String msg;
    private T data;

    public Response() {
    }

    /**
     * 将构造器私有，即外部不能new出该对象
     */


    private Response(T data) {
        this.data = data;
    }

    private Response(String code, T data) {
        this.code = code;
        this.data = data;
    }

    private Response(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Response(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Response<T> success(T data) {
        return result(data, "000000", "SUCCESS");
    }
    public static Response success() {
        return success((Object)null);
    }

    public static Response error() {
        return error(SystemError.SYSTEM_INTERNAL_ERROR);
    }
    public static Response error(IError error, String extMessage) {
        Response response = new Response((Object)null);
        response.code = error.getNamespace() + error.getErrorCode();
        if (extMessage != null) {
            response.msg = String.format("%s, %s", error.getErrorMessage(), extMessage);
        } else {
            response.msg = error.getErrorMessage();
        }

        return response;
    }

    public static <T> Response<T> result(T data, String code, String msg) {
        Response<T> response = new Response();
        response.setCode(code);
        response.setData(data);
        response.setMsg(msg);
        return response;
    }

    public static Response error(IError error) {
        Response response = new Response((Object)null);
        response.code = error.getNamespace() + error.getErrorCode();
        response.msg = error.getErrorMessage();
        return response;
    }
}
