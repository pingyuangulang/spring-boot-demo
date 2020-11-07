package com.five.monkey.common.enums;

/**
 * @author jim
 * @date 2020/11/7 10:17
 */
public enum DeletedEnum {

    VALID("0"),
    INVALID("1");

    private String deleted;

    DeletedEnum(String deleted) {
        this.deleted = deleted;
    }

    public String getDeleted() {
        return this.deleted;
    }
}
