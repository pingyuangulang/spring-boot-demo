package com.five.monkey.mongo.service.impl;

import com.five.monkey.common.cmd.MongoCmd;
import com.five.monkey.common.constants.BootConstant;
import com.five.monkey.common.vo.MongoVo;
import com.five.monkey.mongo.service.IMongoService;
import com.mongodb.client.result.UpdateResult;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.bson.BsonBoolean;
import org.bson.BsonDocument;
import org.bson.BsonValue;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import sun.misc.VM;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
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
        List<MongoVo> mongoVoList;
        if (Objects.nonNull(cmd)) {
            Query query = this.getQuery(cmd);
            mongoVoList = mongoTemplate.find(query, MongoVo.class, COLLECTION_NAME);
        } else {
            mongoVoList = mongoTemplate.findAll(MongoVo.class, COLLECTION_NAME);
        }
        return mongoVoList;
    }

    /**
     * _id存在则更新,不存在则插入
     *
     * @param cmd
     * @return
     */
    @Override
    public MongoVo save(MongoCmd cmd) {
        MongoVo vo = this.mongoCmd2MongoVo(cmd);
        mongoTemplate.save(vo, COLLECTION_NAME);
        return vo;
    }

    /**
     * 根据query条件更新
     * @param query
     * @param cmd
     * @return
     */
    @Override
    public List<MongoVo> update(MongoCmd query, MongoCmd cmd) {
        Query query1 = getQuery(query);
        Update update = getUpdate(cmd);
        UpdateResult updateResult = mongoTemplate.updateMulti(query1, update, COLLECTION_NAME);
        BsonValue bsonValue = updateResult.getUpsertedId();
        return null;
    }

    private Query getQuery(MongoCmd query) {
        Criteria criteria = new Criteria();
        if (StringUtils.isNotBlank(query.get_id())) {
            criteria.and(BootConstant.MongoKey._ID).is(query.get_id());
        }
        if (StringUtils.isNotBlank(query.getName())) {
            criteria.and(BootConstant.MongoKey.NAME).is(query.getName());
        }
        if (StringUtils.isNotBlank(query.getSex())) {
            criteria.and(BootConstant.MongoKey.SEX).is(query.getSex());
        }
        if (Objects.nonNull(query.getAge()) && query.getAge() > 0) {
            criteria.and(BootConstant.MongoKey.AGE).gte(query.getAge());
        }
        return new Query(criteria);
    }

    private Update getUpdate(MongoCmd cmd) {
        Update update = new Update();
        if (StringUtils.isNotBlank(cmd.getName())) {
            update.set(BootConstant.MongoKey.NAME, cmd.getName());
        }
        if (StringUtils.isNotBlank(cmd.getSex())) {
            update.set(BootConstant.MongoKey.SEX, cmd.getSex());
        }
        if (Objects.nonNull(cmd.getAge()) && cmd.getAge() > 0) {
            update.set(BootConstant.MongoKey.AGE, cmd.getAge());
        }
        if (CollectionUtils.isNotEmpty(cmd.getAddress())) {
            update.pushAll(BootConstant.MongoKey.ADDRESS, cmd.getAddress().toArray());
        }
        return update;
    }

    private MongoVo mongoCmd2MongoVo(MongoCmd cmd) {
        MongoVo vo = new MongoVo();
        BeanUtils.copyProperties(cmd, vo);
        vo.setCreateTime(new Date());
        return vo;
    }
}
