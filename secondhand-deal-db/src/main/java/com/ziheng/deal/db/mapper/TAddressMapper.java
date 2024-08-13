package com.ziheng.deal.db.mapper;

import com.ziheng.deal.common.domain.DTO.AddressDTO;
import com.ziheng.deal.db.entity.TAddress;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Administrator
* @description 针对表【t_address】的数据库操作Mapper
* @createDate 2024-07-19 21:33:59
* @Entity com.ziheng.deal.entity.TAddress
*/
public interface TAddressMapper extends BaseMapper<TAddress> {

    // 根据用户id 查询所有的收货地址
    List<AddressDTO> getAllAddress(@Param("id") Integer id);


}




