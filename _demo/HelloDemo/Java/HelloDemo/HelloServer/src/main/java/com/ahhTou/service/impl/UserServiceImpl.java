package com.ahhtou.service.impl;

import com.ahhtou.bean.User;
import com.ahhtou.mapper.UserMapper;
import com.ahhtou.service.MailService;
import com.ahhtou.service.UserService;
import com.ahhtou.exception.ReturnException;
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
        return res.isEmpty();
    }

    @Override
    @Transactional
    public Boolean register(User user) {
        String username = user.getUsername();
        String code = user.getCode();
        String email = user.getEmail();

        // 检查验证码是否有效
        if (!mailService.checkVCode(email, code)) throw new RuntimeException("验证码已经失效");

        // 检查用户名是否唯一
        checkUsernameUnique(username);
        // 注册邮箱是否唯一
        checkEmailUnique(email);

        Integer register = userMapper.register(user);

        return register > 0;
    }

    @Override
    public User getUserMsgByUsername(String username) throws ReturnException {
        User res = userMapper.getUserMsgByUsername(username);
        if (res == null) throw new ReturnException("获取注册信息失败", 400);
        return res;
    }

    @Override
    public User loginAndBackUser(User user) throws ReturnException {

        List<User> dbRes = userMapper.getUserByLogin(user);
        if (dbRes.size() == 0) throw new ReturnException("密码核对失败", 400);
        if (dbRes.size() > 1) throw new ReturnException("数据库异常", 500);
        return dbRes.get(0);

    }

    @Override
    public User getUserByUsername(String username) throws ReturnException {

        List<User> dbRes = userMapper.getUserByUsername(username);
        if (dbRes.size() == 0) throw new ReturnException("密码核对失败", 400);
        if (dbRes.size() > 1) throw new ReturnException("数据库异常", 500);
        return dbRes.get(0);

    }
}
