package com.ziheng.deal.db.service;

import com.ziheng.deal.common.domain.DTO.provinceDTO;
import com.ziheng.deal.db.entity.Province;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author Administrator
* @description 针对表【province】的数据库操作Service
* @createDate 2024-07-18 16:41:52
*/
public interface ProvinceService extends IService<Province> {

    // 获取所有省级信息
    List<provinceDTO> getAllProvince();

    // 根据某一个省级城市获取到所有的市级城市
    List<provinceDTO> getProvinceCodeAllCity(long code);

    // 根据某一个市级城市获取到所有的区
    List<provinceDTO> getCityCodeAllArea(long code);

    // 根据某一个区获取到所有的街道
    List<provinceDTO> getAreaAllResidentialDistrict(long code);

}
