package com.five.monkey.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author jim
 * @date 2020/12/2 17:08
 */
@Data
public class MongoVo {

    private String name;

    private Integer age;

    private String sex;

    private List<String> address;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private String _id;

    private String _class;
}
