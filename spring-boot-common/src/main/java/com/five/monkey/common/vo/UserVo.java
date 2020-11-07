package com.five.monkey.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author jim
 * @date 2020/11/7 10:00
 */
@Data
public class UserVo {

    private Long id;

    private String name;

    private Integer age;

    private String sex;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
