package com.five.monkey.shard.util;

import com.five.monkey.common.cmd.OrderAddCmd;
import com.five.monkey.common.cmd.ProductAddCmd;
import com.five.monkey.common.cmd.UserAddCmd;
import com.five.monkey.common.enums.DeletedEnum;
import com.five.monkey.common.vo.OrderVo;
import com.five.monkey.common.vo.ProductVo;
import com.five.monkey.common.vo.UserVo;
import com.five.monkey.dal.model.Order;
import com.five.monkey.dal.model.Product;
import com.five.monkey.dal.model.User;
import org.springframework.beans.BeanUtils;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * @author jim
 * @date 2020/11/7 10:27
 */
public class ModelUtils {

    private static final String ERROR_MESSAGE = "class:%s,method:%s,parameter is null";

    /**
     * user --> userVo
     * @param user enable null
     */
    public static UserVo user2Vo(User user) {
        if (Objects.isNull(user)) {
            return null;
        }
        UserVo vo = new UserVo();
        BeanUtils.copyProperties(user, vo);
        return vo;
    }

    /**
     * userVo --> user
     * @param addCmd must not null.
     */
    public static User userAddCmd2User(UserAddCmd addCmd) {
        if (Objects.isNull(addCmd)) {
            throw new NullPointerException(String.format(ERROR_MESSAGE, ModelUtils.class.getName(), "userAddCmd2User"));
        }
        User user = new User();
        user.setName(addCmd.getName());
        user.setAge(addCmd.getAge());
        user.setSex(String.valueOf(addCmd.getSex()));
        user.setCreateTime(new Date());
        return user;
    }

    /**
     * product --> productVo
     * @param product enable null
     */
    public static ProductVo product2Vo(Product product) {
        ProductVo vo = null;
        if (Objects.nonNull(product)) {
            vo = new ProductVo();
            BeanUtils.copyProperties(product, vo);
        }
        return vo;
    }

    /**
     * productAddCmd --> product
     * @param addCmd disable null
     */
    public static Product productAddCmd2Product(ProductAddCmd addCmd) {
        if (Objects.isNull(addCmd)) {
            throw new NullPointerException(String.format(ERROR_MESSAGE, ModelUtils.class.getName(), "productAddCmd2Product"));
        }
        Product product = new Product();
        product.setName(addCmd.getName());
        product.setVersion(addCmd.getVersion());
        product.setCreateTime(new Date());
        product.setPrice(new BigDecimal(addCmd.getPrice()));
        product.setDeleted(DeletedEnum.VALID.getDeleted());
        return product;
    }

    /**
     * order --> orderVo
     * @param order nullable
     */
    public static OrderVo order2Vo(Order order) {
        OrderVo vo;
        if (Objects.isNull(order)) {
            vo = null;
        } else {
            vo = new OrderVo();
            BeanUtils.copyProperties(order, vo);
        }
        return vo;
    }

    /**
     * orderVo --> order
     * @param addCmd notnull
     */
    public static Order orderAddCmd2Order(OrderAddCmd addCmd) {
        if (Objects.isNull(addCmd)) {
            throw new NullPointerException(String.format(ERROR_MESSAGE, ModelUtils.class.getName(), "orderAddCmd2Order"));
        }
        Order order = new Order();
        order.setProductId(addCmd.getProductId());
        order.setUserId(addCmd.getUserId());
        order.setNum(addCmd.getNum());
        order.setCreateTime(new Date());
        return order;
    }
}
