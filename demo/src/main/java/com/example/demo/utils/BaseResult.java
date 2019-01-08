package com.example.demo.utils;

import java.util.HashMap;
import java.util.Map;

public class BaseResult extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public BaseResult() {
        put("code", 0);
        put("msg", "success");
    }

    public static BaseResult error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static BaseResult error(String msg) {
        return error(500, msg);
    }

    public static BaseResult error(int code, String msg) {
        BaseResult br = new BaseResult();
        br.put("code", code);
        br.put("msg", msg);
        return br;
    }

    public static BaseResult ok(String msg) {
        BaseResult br = new BaseResult();
        br.put("msg", msg);
        return br;
    }

    public static BaseResult ok(Map<String, Object> map) {
        BaseResult br = new BaseResult();
        br.putAll(map);
        return br;
    }

    public static BaseResult ok() {
        return new BaseResult();
    }

    @Override
    public BaseResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}