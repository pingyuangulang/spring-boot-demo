package com.five.monkey.shard.controller;

import com.five.monkey.common.cmd.UserAddCmd;
import com.five.monkey.common.vo.UserVo;
import com.five.monkey.common.vo.base.BaseListResult;
import com.five.monkey.common.vo.base.BaseSingleResult;
import com.five.monkey.shard.service.IUserService;
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
 * @date 2020/11/6 10:00
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/list/by/name")
    public BaseListResult<UserVo> listByName(@RequestParam("name") String name) {
        List<UserVo> userVoList = userService.findByName(name);
        return new BaseListResult<>(userVoList);
    }

    @PutMapping("/add")
    public BaseSingleResult<UserVo> add(@RequestBody @Valid UserAddCmd addCmd) {
        UserVo vo = userService.insert(addCmd);
        return new BaseSingleResult<>(vo);
    }
}
