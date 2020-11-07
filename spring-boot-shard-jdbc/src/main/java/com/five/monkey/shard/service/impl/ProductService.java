package com.five.monkey.shard.service.impl;

import com.five.monkey.common.cmd.ProductAddCmd;
import com.five.monkey.common.vo.ProductVo;
import com.five.monkey.dal.mapper.ProductMapper;
import com.five.monkey.dal.model.Product;
import com.five.monkey.dal.model.ProductExample;
import com.five.monkey.shard.service.IProductService;
import com.five.monkey.shard.util.ModelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jim
 * @date 2020/11/6 20:05
 */
@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<ProductVo> findByNameAndVersion(String name, String version) {
        ProductExample example = new ProductExample();
        ProductExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name).andVersionEqualTo(version);
        List<Product> productList = productMapper.selectByExample(example);
        return productList.stream().map(ModelUtils::product2Vo).collect(Collectors.toList());
    }

    @Override
    public ProductVo insert(ProductAddCmd productAddCmd) {
        Product product = ModelUtils.productAddCmd2Product(productAddCmd);
        productMapper.insert(product);
        return ModelUtils.product2Vo(product);
    }
}
