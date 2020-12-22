package com.five.monkey.common.cmd;

import lombok.Data;

import java.util.List;

/**
 * @author jim
 * @date 2020/12/2 17:12
 */
@Data
public class MongoCmd {

    private String _id;

    private String name;

    private Integer age;

    private String sex;

    private List<String> address;
}
