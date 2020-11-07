package com.five.monkey.common.cmd;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author jim
 * @date 2020/11/7 11:02
 */
@Data
public class ProductAddCmd {

    @NotBlank(message = "商品名称不能为空")
    @Size(min = 1, max = 60, message = "商品名称字符个数超出[1,60]范围")
    private String name;

    @NotBlank(message = "商品版本号不能为空")
    @Size(min = 1, max = 60, message = "商品版本号字符个数超出[1,60]范围")
    private String version;

    @Pattern(regexp = "^[+]{0,1}(\\d+)$|^[+]{0,1}(\\d+\\.\\d+)$", message = "商品价格非法")
    private String price;
}
