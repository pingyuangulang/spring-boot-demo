package com.five.monkey.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;

/**
 * @author jim
 * @date 2020/11/7 10:20
 */
@Data
public class ProductVo {

    private Long id;

    private String name;

    private String version;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private String price;

    private String deleted;
}
