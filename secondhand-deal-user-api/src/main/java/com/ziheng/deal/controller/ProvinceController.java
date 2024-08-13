package com.ziheng.deal.controller;

import com.ziheng.deal.common.domain.DTO.provinceDTO;
import com.ziheng.deal.common.resp.ResultJsonData;
import com.ziheng.deal.db.service.ProvinceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("province")
@Tag(name = "收货地址省市区街道信息获取")
public class ProvinceController {
    @Resource
    private ProvinceService provinceService;

    /**
     * 获取所有的省级信息
     * @return 省级信息
     */
    @GetMapping()
    @Operation(summary = "省级城市信息和行政编码获取接口")
    public ResultJsonData<List<provinceDTO>> getAllProvince(){
        List<provinceDTO> provinceDTOList = provinceService.getAllProvince();

        return ResultJsonData.success(provinceDTOList);

    }

    /**
     * 根据省级城市行政码获取到所有的市级城市
     * @param code 省级城市行政代码
     * @return 市级城市信息
     */
    @GetMapping("city/{code}")
    @Operation(summary = "根据省级城市行政码获取到所有的市级城市接口")
    public ResultJsonData<List<provinceDTO>> getCity(@PathVariable("code") long code){
        List<provinceDTO> city = provinceService.getProvinceCodeAllCity(code);

        return ResultJsonData.success(city);

    }


    /**
     * 根据某一个市级城市行政代码获取到区
     * @param code 市级城市行政代码
     * @return 区级城市信息
     */
    @GetMapping("area/{code}")
    @Operation(summary = "根据某一个市级城市行政代码获取到区接口")
    public ResultJsonData<List<provinceDTO>> getArea(@PathVariable("code") long code){
        List<provinceDTO> city = provinceService.getCityCodeAllArea(code);

        return ResultJsonData.success(city);

    }

    /**
     * 根据区级行政代码获取到街道
     * @param code 区级行政代码
     * @return 街道信息
     */
    @GetMapping("residential/{code}")
    @Operation(summary = "根据区级行政代码获取到街道信息接口")
    public ResultJsonData<List<provinceDTO>> getResidentialDistrict(@PathVariable("code") long code){
        List<provinceDTO> city = provinceService.getAreaAllResidentialDistrict(code);

        return ResultJsonData.success(city);

    }



}
