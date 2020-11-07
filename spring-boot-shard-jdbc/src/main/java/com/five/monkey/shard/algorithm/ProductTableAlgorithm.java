package com.five.monkey.shard.algorithm;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * product分表策略
 * @author jim
 * @date 2020/11/6 17:24
 */
public class ProductTableAlgorithm implements PreciseShardingAlgorithm<String> {

    /**
     * Sharding.
     *
     * @param availableTargetNames available data sources or tables's names
     * @param shardingValue        sharding value
     * @return sharding result for data source or table's name
     */
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<String> shardingValue) {
        String version = shardingValue.getValue();
        int index = Math.abs(version.hashCode()) % 5 + 1;
        return "product_" + index;
    }
}
