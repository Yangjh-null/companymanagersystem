package com.companymanager.z_resultpackage;

public class Result<T> {
    private T data;
    private Integer code;
    private String msg;


    //采用静态方法 封装返回结果
    //1 成功无返回数据
    public static <T> Result<T> successNoData(ResultStatus resultStatus){
        Result<T> result = new Result<>();
        result.setCode(resultStatus.getCode());
        result.setMsg(resultStatus.getMsg());
        return result;
    }
    //2 成功且有返回数据
    public static <T> Result<T> successAndData(ResultStatus resultStatus,T data){
        Result<T> result = new Result<>();
        result.setCode(resultStatus.getCode());
        result.setMsg(resultStatus.getMsg());
        result.setData(data);
        return result;
    }
    //3 失败
    public static<T> Result<T> fail(ResultStatus resultStatus){
        Result<T> result = new Result<>();
        result.setCode(resultStatus.getCode());
        result.setMsg(resultStatus.getMsg());
        return result;

    }


    public Result() {

    }

    public Result(T data, Integer code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
