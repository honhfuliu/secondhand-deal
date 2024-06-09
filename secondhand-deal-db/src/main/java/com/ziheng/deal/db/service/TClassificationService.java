package com.ziheng.deal.db.service;

import com.ziheng.deal.common.domain.DTO.ClassificationDTO;
import com.ziheng.deal.db.entity.TClassification;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Administrator
* @description 针对表【t_classification】的数据库操作Service
* @createDate 2024-04-25 20:51:45
*/
public interface TClassificationService extends IService<TClassification> {


    // 分页查询所有分类
    List<ClassificationDTO>  getAllClassification();

    // 分类添加
    void AddClassification(TClassification tClassification, String username);

    // 分类查询出所有分类（前2级）
    List<ClassificationDTO>  GetClassificationTow();

    // 根据某个分类的id查询出他的父类
    ClassificationDTO getByIdClassificationInfo(Integer id);

    // 分类修改
    void getByIdUpdateClassification(TClassification classification, String username);

    // 根据id删除分类实现
    void getByIdDeleteClassification(Integer id);



}
