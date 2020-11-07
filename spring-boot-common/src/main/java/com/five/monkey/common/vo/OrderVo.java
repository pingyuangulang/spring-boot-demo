package com.five.monkey.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author jim
 * @date 2020/11/7 10:21
 */
@Data
public class OrderVo {

    private String id;

    private Long productId;

    private Long userId;

    private Integer num;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
