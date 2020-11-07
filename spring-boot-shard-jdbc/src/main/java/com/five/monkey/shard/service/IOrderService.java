package com.five.monkey.shard.service;

import com.five.monkey.common.cmd.OrderAddCmd;
import com.five.monkey.common.vo.OrderVo;

import java.util.List;

/**
 * @author jim
 * @date 2020/11/7 11:57
 */
public interface IOrderService {

    List<OrderVo> findByProductIdAndUserId(Long productId, Long userId);

    OrderVo insert(OrderAddCmd orderAddCmd);
}
