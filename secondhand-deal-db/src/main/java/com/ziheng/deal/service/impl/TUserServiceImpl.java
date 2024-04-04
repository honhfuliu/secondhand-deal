package com.ziheng.deal.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ziheng.deal.entity.TUser;
import com.ziheng.deal.service.TUserService;
import com.ziheng.deal.mapper.TUserMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【t_user(用户表)】的数据库操作Service实现
* @createDate 2024-04-07 20:44:13
*/
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser>
    implements TUserService{

    @Resource
    private TUserMapper userMapper;


}




