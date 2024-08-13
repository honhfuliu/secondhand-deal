package com.ziheng.deal.db.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ziheng.deal.common.ex.customizeErorr.controllerErorr.*;
import com.ziheng.deal.db.entity.CommodityPicture;
import com.ziheng.deal.db.service.CommodityPictureService;
import com.ziheng.deal.db.mapper.CommodityPictureMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
* @author Administrator
* @description 针对表【commodity_picture(商品图片存储表)】的数据库操作Service实现
* @createDate 2024-05-27 22:38:11
*/
@Service
public class CommodityPictureServiceImpl extends ServiceImpl<CommodityPictureMapper, CommodityPicture>
    implements CommodityPictureService{

    //设置上传文件的最大值
    public static final int AVATAR_MAX_SIZE = 2 * 1024 * 1024;
    // 设置上传文件的类型
    public static final List<String> AVATAR_TYPE = new ArrayList<>();
    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
    }


    /**
     * 商品主图存储
     * @param path 存储路径
     * @param files 存储文件
     * @return 存储文件路径
     */
    @Override
    public ArrayList<String> commodityPictureAdd(String path, List<MultipartFile> files) {

        ArrayList<String> filePaths = new ArrayList<>();

        for (MultipartFile file : files) {
            String s = commodityDetailsPicture(path, file);
            filePaths.add("upload/" + s);
        }

        return filePaths;
    }

    /**
     * 商品详情图上传处理
     * @param path 存储路径
     * @param file 图片文件
     * @return 存储路径
     */
    @Override
    public String commodityDetailsPicture(String path, MultipartFile file) {
        //判断文件是否为空
        if (file != null && file.isEmpty()) {
            throw new FileEmptyException("文件不能为空");
        }

        // 判断文件大小
        if (file.getSize() > AVATAR_MAX_SIZE) {
            throw new FileSizeException("文件超过规定大小");
        }

        // 判断文件类型
        String type = file.getContentType();
        if (!AVATAR_TYPE.contains(type)) {
            throw new FileTypeException("文件类型错误");
        }

        // path路径是否存在
        File dir = new File(path);
        if (!dir.exists()) { // 检测目录是否存在
            dir.mkdirs(); //创建目录
        }

        // 获取到这个文件的名称， UUID工具生成一个新的随机字符串作为名称
        String filename = file.getOriginalFilename(); // 包含名称+后缀
        int i = filename.lastIndexOf("."); // 获取.所在的索引
        String suffix = filename.substring(i);
        String uploadName = UUID.randomUUID().toString().toUpperCase() + suffix;

        // 创建文件对象，表示要保存的目标文件
        File dest = new File(dir, uploadName);

        // 将参数file中数据写入到这个空文件中
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new FileUploadIOException("文件读写异常");
        } catch (FileStateException e) {
            throw new FileStateException("文件状态异常");
        }

        return uploadName;
    }
}




