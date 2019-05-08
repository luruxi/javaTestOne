package enn.testone.exception;

import enn.testone.enums.ExceptionEnum;

public class Myexception extends RuntimeException {
    private Integer code;

    public Myexception(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.code = exceptionEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
