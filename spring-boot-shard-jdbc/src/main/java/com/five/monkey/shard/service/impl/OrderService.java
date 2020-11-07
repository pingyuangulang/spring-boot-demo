package com.five.monkey.shard.service.impl;

import com.five.monkey.common.cmd.OrderAddCmd;
import com.five.monkey.common.vo.OrderVo;
import com.five.monkey.dal.mapper.OrderMapper;
import com.five.monkey.dal.model.Order;
import com.five.monkey.dal.model.OrderExample;
import com.five.monkey.shard.service.IOrderService;
import com.five.monkey.shard.util.ModelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jim
 * @date 2020/11/7 11:58
 */
@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<OrderVo> findByProductIdAndUserId(Long productId, Long userId) {
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        criteria.andProductIdEqualTo(productId).andUserIdEqualTo(userId);
        List<Order> orderList = orderMapper.selectByExample(example);
        return orderList.stream().map(ModelUtils::order2Vo).collect(Collectors.toList());
    }

    @Override
    public OrderVo insert(OrderAddCmd orderAddCmd) {
        Order order = ModelUtils.orderAddCmd2Order(orderAddCmd);
        orderMapper.insert(order);
        return ModelUtils.order2Vo(order);
    }
}
