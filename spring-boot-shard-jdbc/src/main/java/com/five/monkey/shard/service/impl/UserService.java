package com.five.monkey.shard.service.impl;

import com.five.monkey.common.cmd.UserAddCmd;
import com.five.monkey.common.vo.UserVo;
import com.five.monkey.dal.mapper.UserMapper;
import com.five.monkey.dal.model.User;
import com.five.monkey.dal.model.UserExample;
import com.five.monkey.shard.service.IUserService;
import com.five.monkey.shard.util.ModelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jim
 * @date 2020/11/6 9:33
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserVo> findByName(String name) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);
        List<User> userList = userMapper.selectByExample(example);
        return userList.stream().map(ModelUtils::user2Vo).collect(Collectors.toList());
    }

    @Override
    public UserVo insert(UserAddCmd userAddCmd) {
        User user = ModelUtils.userAddCmd2User(userAddCmd);
        userMapper.insert(user);
        return ModelUtils.user2Vo(user);
    }
}
