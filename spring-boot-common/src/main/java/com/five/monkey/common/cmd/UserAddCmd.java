package com.five.monkey.common.cmd;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * @author jim
 * @date 2020/11/7 11:32
 */
@Data
public class UserAddCmd {

    @NotBlank(message = "用户名称不能为空")
    @Size(min = 1, max = 60, message = "用户名称字符个数超出[1,60]范围")
    private String name;

    @Range(min = 18L, max = 70L, message = "用户年龄超出[18,70]范围")
    private int age;

    @Range(min = 1L, max = 2L, message = "用户性别输入错误:1-->男;2-->女")
    private int sex;
}
