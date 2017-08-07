package net.liuchenfei.ssitest.utils;

/**
 * Created by liuchenfei on 2017/8/6.
 */
public class Result<T> {
    private T value;

    private boolean success;

    private String error;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
