package com.ziheng.deal.db.service;

import com.ziheng.deal.db.entity.CommodityPicture;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
* @author Administrator
* @description 针对表【commodity_picture(商品图片存储表)】的数据库操作Service
* @createDate 2024-05-27 22:38:11
*/
public interface CommodityPictureService extends IService<CommodityPicture> {
    // 商品主图图片存储接口
    ArrayList<String> commodityPictureAdd(String path, List<MultipartFile> files);

    // 商品详情图存储接口
    String commodityDetailsPicture(String path, MultipartFile file);

}
