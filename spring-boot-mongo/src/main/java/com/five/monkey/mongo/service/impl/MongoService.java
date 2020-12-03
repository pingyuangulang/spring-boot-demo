package com.five.monkey.mongo.service.impl;

import com.five.monkey.common.cmd.MongoCmd;
import com.five.monkey.common.constants.BootConstant;
import com.five.monkey.common.vo.MongoVo;
import com.five.monkey.mongo.service.IMongoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jim
 * @date 2020/12/2 17:06
 */
@Service
public class MongoService implements IMongoService {

    private static final String COLLECTION_NAME = "t_mongo_info";

    @Autowired
    private MongoTemplate mongoTemplate;


    /**
     * insert to mongoDB
     *
     * @param cmd
     * @return
     */
    @Override
    public MongoVo insert(MongoCmd cmd) {
        MongoVo vo = this.mongoCmd2MongoVo(cmd);
        mongoTemplate.insert(vo, COLLECTION_NAME);
        return vo;
    }

    /**
     * 批量insert to mongoDB
     *
     * @param cmdList
     * @return
     */
    @Override
    public List<MongoVo> batchInsert(List<MongoCmd> cmdList) {
        List<MongoVo> voList = cmdList.stream().map(this::mongoCmd2MongoVo).collect(Collectors.toList());
        mongoTemplate.insert(voList, COLLECTION_NAME);
        return voList;
    }

    /**
     * query from mongoDB
     *
     * @param cmd
     * @return
     */
    @Override
    public List<MongoVo> query(MongoCmd cmd) {
        Criteria criteria = Criteria.where(BootConstant.MongoKey.NAME).is(cmd.getName());
        criteria.and(BootConstant.MongoKey.AGE).gte(cmd.getAge());
        return mongoTemplate.find(new Query(criteria), MongoVo.class, COLLECTION_NAME);
    }

    private MongoVo mongoCmd2MongoVo(MongoCmd cmd) {
        MongoVo vo = new MongoVo();
        BeanUtils.copyProperties(cmd, vo);
        vo.setCreateTime(new Date());
        return vo;
    }
}
