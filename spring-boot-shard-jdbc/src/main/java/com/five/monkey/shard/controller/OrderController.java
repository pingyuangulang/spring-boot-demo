package com.five.monkey.shard.controller;

import com.five.monkey.common.cmd.OrderAddCmd;
import com.five.monkey.common.vo.OrderVo;
import com.five.monkey.common.vo.base.BaseListResult;
import com.five.monkey.common.vo.base.BaseSingleResult;
import com.five.monkey.shard.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author jim
 * @date 2020/11/7 11:53
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @GetMapping("/list/by/{productId}/{userId}")
    public BaseListResult<OrderVo> list(@PathVariable("productId") Long productId, @PathVariable("userId") Long userId) {
        List<OrderVo> orderVoList = orderService.findByProductIdAndUserId(productId, userId);
        return new BaseListResult<>(orderVoList);
    }

    @PutMapping("/add")
    public BaseSingleResult<OrderVo> add(@RequestBody @Valid OrderAddCmd addCmd) {
        OrderVo vo = orderService.insert(addCmd);
        return new BaseSingleResult<>(vo);
    }
}
