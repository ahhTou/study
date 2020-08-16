package com.ahhTou.service.impl;

import com.ahhTou.bean.User;
import com.ahhTou.mapper.UserMapper;
import com.ahhTou.service.UserService;
import com.ahhTou.utils.MyTools;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@DependsOn("applicationContextHolder")
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Resource
    MyTools myTools;

    @Override
    public Boolean checkUsernameUnique(String username) {
        List<String> res = userMapper.checkUsernameUnique(username);
        return res.isEmpty();
    }

    @Override
    public Boolean checkEmailUnique(String email) {
        List<String> res = userMapper.checkEmailUnique(email);
        return res.isEmpty();
    }

    @Override
    @Transactional
    public Boolean register(User user) {
        String username = user.getUsername();
        String code = user.getCode();
        String email = user.getEmail();

        // 检查验证码是否有效
        if (!myTools.checkVCode(email, code)) return false;

        // 检查用户名是否唯一
        if (!checkUsernameUnique(username)) return false;

        // 注册邮箱是否唯一
        if (!checkEmailUnique(email)) return false;

        // 注册
        Integer register = userMapper.register(user);

        return register > 0;
    }

    @Override
    public Object getUserMsgByUsername(String username) {
        User res = userMapper.getUserMsgByUsername(username);
        if (res == null) return false;
        else return res;
    }
}
