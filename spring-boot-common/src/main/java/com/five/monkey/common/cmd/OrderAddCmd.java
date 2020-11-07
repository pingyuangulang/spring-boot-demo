package com.five.monkey.common.cmd;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * @author jim
 * @date 2020/11/7 11:50
 */
@Data
public class OrderAddCmd {

    @NotNull(message = "商品id不能为空")
    private Long productId;

    @NotNull(message = "用户id不能为空")
    private Long userId;

    @Range(min = 1L, max = 5L, message = "每次下单数量不能超出[1,5]范围")
    private Integer num;
}
