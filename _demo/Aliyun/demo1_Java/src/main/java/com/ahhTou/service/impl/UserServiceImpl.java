package com.ahhTou.service.impl;

import com.ahhTou.bean.User;
import com.ahhTou.mapper.UserMapper;
import com.ahhTou.service.MailService;
import com.ahhTou.service.UserService;
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
    MailService mailService;

    @Override
    public Boolean checkUsernameUnique(String username) {
        if (username == null) throw new RuntimeException("用户名为空");
        List<String> res = userMapper.checkUsernameUnique(username);
        if (!res.isEmpty()) throw new RuntimeException("用户名异不唯一");
        return true;
    }

    @Override
    public Boolean checkEmailUnique(String email) {
        if (email == null) throw new RuntimeException("邮箱为空");
        List<String> res = userMapper.checkEmailUnique(email);
        if (!res.isEmpty()) throw new RuntimeException("邮箱已被注册");
        return true;
    }

    @Override
    @Transactional
    public Boolean register(User user) {
        String username = user.getUsername();
        String code = user.getCode();
        String email = user.getEmail();

        // 检查验证码是否有效
        if (!mailService.checkVCode(email, code)) return false;

        // 检查用户名是否唯一
        checkUsernameUnique(username);
        // 注册邮箱是否唯一
        checkEmailUnique(email);

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
