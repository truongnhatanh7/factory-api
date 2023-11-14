package com.truongnhatanh7.manufacturerservice.entity;

public enum MPOStatus {
    CREATED("C"), PROCESSING("P"), ROLLBACK("R"), APPROVED("A");
    private String code;

    private MPOStatus(String c) {
        this.code = c;
    }

    public String getCode() {
        return code;
    }
}
