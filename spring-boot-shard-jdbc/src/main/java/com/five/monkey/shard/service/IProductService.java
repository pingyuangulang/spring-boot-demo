package com.five.monkey.shard.service;

import com.five.monkey.common.cmd.ProductAddCmd;
import com.five.monkey.common.vo.ProductVo;
import java.util.List;

/**
 * @author jim
 * @date 2020/11/6 20:05
 */
public interface IProductService {

    List<ProductVo> findByNameAndVersion(String name, String version);

    ProductVo insert(ProductAddCmd productAddCmd);
}
