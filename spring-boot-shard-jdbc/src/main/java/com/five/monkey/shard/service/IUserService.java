package com.five.monkey.shard.service;

import com.five.monkey.common.cmd.UserAddCmd;
import com.five.monkey.common.vo.UserVo;
import java.util.List;

/**
 * @author jim
 * @date 2020/11/6 9:32
 */
public interface IUserService {

    List<UserVo> findByName(String name);

    UserVo insert(UserAddCmd userAddCmd);
}
