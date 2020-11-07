package com.five.monkey.shard.controller;

import com.five.monkey.common.cmd.ProductAddCmd;
import com.five.monkey.common.vo.ProductVo;
import com.five.monkey.common.vo.base.BaseListResult;
import com.five.monkey.common.vo.base.BaseSingleResult;
import com.five.monkey.shard.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

/**
 * @author jim
 * @date 2020/11/6 20:11
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("/list/by/name/version")
    public BaseListResult<ProductVo> list(@RequestParam("name") String name, @RequestParam("version") String version) {
        List<ProductVo> productVoList = productService.findByNameAndVersion(name, version);
        return new BaseListResult<>(productVoList);
    }

    @PutMapping("/add")
    public BaseSingleResult<ProductVo> add(@RequestBody @Valid ProductAddCmd addCmd) {
        ProductVo vo = productService.insert(addCmd);
        return new BaseSingleResult<>(vo);
    }
}
