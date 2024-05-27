package com.ziheng.deal.db.service;

import com.ziheng.deal.common.domain.VO.UserRegisterVO;
import com.ziheng.deal.db.entity.TAdmin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Administrator
* @description 针对表【t_admin】的数据库操作Service
* @createDate 2024-04-18 21:37:41
*/
public interface TAdminService extends IService<TAdmin> {
    //管理员登录验证
    String adminLoginVerification(UserRegisterVO userRegisterVO);
}
