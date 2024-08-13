package com.ziheng.deal.db.service;

import com.ziheng.deal.common.domain.DTO.AddressDTO;
import com.ziheng.deal.common.domain.VO.AddressVO;
import com.ziheng.deal.db.entity.TAddress;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Administrator
* @description 针对表【t_address】的数据库操作Service
* @createDate 2024-07-19 21:33:59
*/
public interface TAddressService extends IService<TAddress> {
    // 根据用户id添加收货地址
    void AddShippingAddressBasedUserId(Integer userId, String createUser, AddressVO addressVO);

    // 根据用户id获取到该用户的所有收货地址
    List<AddressDTO> getAllAddress(Integer userId);

    //根据用户收货地址id修改收货地址信息
    void updateAddress(AddressVO addressVO, Integer userId);

    // 根据用户id和收货地址id删除收货地址
    void deleteAddress(Integer userId, Integer addressId);

    // 设置默认收货地址
    void setAcquiesceAddress(Integer newAddressId, Integer userId);

}
