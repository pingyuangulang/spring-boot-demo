package com.five.monkey.common.enums;

/**
 * @author jim
 * @date 2020/11/7 10:18
 */
public enum SexEnum {

    MALE("1"),
    FEMALE("2");

    private String sex;

    SexEnum(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return this.sex;
    }
}
