package com.ziheng.deal.db.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ziheng.deal.common.domain.DTO.provinceDTO;
import com.ziheng.deal.db.entity.Province;
import com.ziheng.deal.db.service.ProvinceService;
import com.ziheng.deal.db.mapper.ProvinceMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Administrator
* @description 针对表【province】的数据库操作Service实现
* @createDate 2024-07-18 16:41:52
*/
@Service
public class ProvinceServiceImpl extends ServiceImpl<ProvinceMapper, Province>
    implements ProvinceService{

    @Resource
    private ProvinceMapper provinceMapper;

    /**
     * 获取所有的省级城市和code
     * @return 省级城市信息
     */
    @Override
    public List<provinceDTO> getAllProvince() {
        List<provinceDTO> provinceList = provinceMapper.getAllProvince();

        return provinceList;
    }

    /**
     * 获取某一个省下的所有市区
     * @param code 城市行政代码
     * @return 市级信息
     */
    @Override
    public List<provinceDTO> getProvinceCodeAllCity(long code) {

        List<provinceDTO> citys = provinceMapper.getProvinceCodeAllCity(code);

        return citys;
    }

    /**
     * 获取某一个市下的所有区
     * @param code 城市行政代码
     * @return 区信息
     */
    @Override
    public List<provinceDTO> getCityCodeAllArea(long code) {
        List<provinceDTO> areaList = provinceMapper.getCityCodeAllArea(code);
        return areaList;
    }

    /**
     * 获取某一个区下的所有街道信息
     * @param code 城市行政代码
     * @return 街道信息
     */
    @Override
    public List<provinceDTO> getAreaAllResidentialDistrict(long code) {
        List<provinceDTO> list = provinceMapper.getAreaAllResidentialDistrict(code);
        return list;
    }
}




