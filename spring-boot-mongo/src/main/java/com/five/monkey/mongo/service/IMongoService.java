package com.five.monkey.mongo.service;

import com.five.monkey.common.cmd.MongoCmd;
import com.five.monkey.common.vo.MongoVo;

import java.util.List;

/**
 * @author jim
 * @date 2020/12/2 17:06
 */
public interface IMongoService {

    /**
     * insert to mongoDB
     * @param cmd
     * @return
     */
    MongoVo insert(MongoCmd cmd);

    /**
     * 批量insert to mongoDB
     * @param cmdList
     * @return
     */
    List<MongoVo> batchInsert(List<MongoCmd> cmdList);

    /**
     * query from mongoDB
     * @param cmd
     * @return
     */
    List<MongoVo> query(MongoCmd cmd);

    /**
     * _id存在则更新,不存在则插入
     * @param cmd
     * @return
     */
    MongoVo save(MongoCmd cmd);

    /**
     * 根据query条件更新
     * @param query
     * @param cmd
     * @return
     */
    List<MongoVo> update(MongoCmd query, MongoCmd cmd);
}
