package com.five.monkey.mongo.controller;

import com.five.monkey.common.cmd.MongoCmd;
import com.five.monkey.common.vo.MongoVo;
import com.five.monkey.common.vo.base.BaseListResult;
import com.five.monkey.common.vo.base.BaseSingleResult;
import com.five.monkey.mongo.service.IMongoService;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author jim
 * @date 2020/12/2 17:35
 */
@RestController
@RequestMapping("/mongo")
public class MongoController {

    @Autowired
    private IMongoService mongoService;

    /**
     * 构造数据
     * @return
     */
    @PutMapping("/create/data")
    public BaseListResult<MongoVo> createData() {
        List<MongoCmd> cmdList = Lists.newLinkedList();
        for (int i = 0; i < 10; i++) {
            MongoCmd cmd = new MongoCmd();
            cmd.setName(UUID.randomUUID().toString());
            cmd.setAge(18 + i);
            cmd.setSex(i % 2 == 0 ? "female" : "male");
            List<String> address = Lists.newArrayList(cmd.getName() + "==1", cmd.getName() + "==2", cmd.getName() + "==3");
            cmd.setAddress(address);
            cmdList.add(cmd);
        }
        List<MongoVo> voList = mongoService.batchInsert(cmdList);
        return new BaseListResult<>(voList);
    }

    @GetMapping("/query")
    public BaseListResult<MongoVo> query(MongoCmd cmd) {
        List<MongoVo> voList = mongoService.query(cmd);
        return new BaseListResult<>(voList);
    }

    @GetMapping("/query/all")
    public BaseListResult<MongoVo> query() {
        return new BaseListResult<>(mongoService.query(null));
    }

    @PutMapping("/insert")
    public BaseSingleResult<MongoVo> insert(@RequestBody MongoCmd cmd) {
        return new BaseSingleResult<>(mongoService.insert(cmd));
    }

    @PostMapping("/save")
    public BaseSingleResult<MongoVo> save(@RequestBody MongoCmd cmd) {
        if (Objects.isNull(cmd) || StringUtils.isBlank(cmd.get_id())) {
            return new BaseSingleResult<>(500, "parameter \"_id\" is blank", null);
        }
        return new BaseSingleResult<>(mongoService.save(cmd));
    }
}
