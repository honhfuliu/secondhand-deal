package com.ziheng.deal.db.mapper;

import com.ziheng.deal.common.domain.DTO.provinceDTO;
import com.ziheng.deal.db.entity.Province;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

/**
* @author Administrator
* @description 针对表【province】的数据库操作Mapper
* @createDate 2024-07-18 16:41:52
* @Entity com.ziheng.deal.entity.Province
*/
public interface ProvinceMapper extends BaseMapper<Province> {

    // 获取到所有的省级城市
    List<provinceDTO> getAllProvince();


    // 根据某一个省级城市获取到所有的市级城市
    List<provinceDTO> getProvinceCodeAllCity(@Param("code") long code);

    // 根据某一个市级城市获取到所有的区
    List<provinceDTO> getCityCodeAllArea(@Param("code") long code);

    // 根据某一个区获取到所有的街道 residential district
    List<provinceDTO> getAreaAllResidentialDistrict(@Param("code") long code);


}




