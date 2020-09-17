package com.idaas.gateway.model;/**
 * Created by tkmttt on 2019/4/24 14:42.
 */


import lombok.Data;

import java.io.Serializable;

/**
 * @description: 公用的Json响应
 * @author: Bao Hongbin
 **/
@Data
public class JsonResult<T> implements Serializable {
    /**
     * 200 代表成功
     */
    private Integer code = 200;
    /**
     * 信息
     */
    private String msg = "success";
    /**
     * 时间戳
     */
    private long ts;
    /**
     * 返回内容
     */
    private T data;

    public JsonResult() {
        this.ts = System.currentTimeMillis();
    }

    public JsonResult(T data) {
        this.ts = System.currentTimeMillis();
        this.data = data;
    }


    public JsonResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.ts = System.currentTimeMillis();
    }

    public static JsonResult success() {
        return new JsonResult();
    }

    public static JsonResult success(String msg) {
        return new JsonResult(200, msg);
    }

    public static JsonResult success(Object data) {
        return new JsonResult(data);
    }

    public static JsonResult error(Integer code, String msg) {
        return new JsonResult(code, msg);
    }

    public static JsonResult error(String msg) {
        return new JsonResult(4001, msg);
    }

    public static JsonResult error() {
        return new JsonResult(4001, "失败");
    }


}
