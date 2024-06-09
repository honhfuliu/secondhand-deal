package com.ziheng.deal.controller;

import com.ziheng.deal.common.domain.DTO.CommodityDTO;
import com.ziheng.deal.common.domain.DTO.PageDOT;
import com.ziheng.deal.common.domain.TokenInfo;
import com.ziheng.deal.common.domain.VO.commodityPageQueryVO;
import com.ziheng.deal.common.resp.ResultJsonData;
import com.ziheng.deal.common.util.TokenUtil;
import com.ziheng.deal.common.domain.VO.CommodityVO;
import com.ziheng.deal.db.service.CommodityPictureService;
import com.ziheng.deal.db.service.TCommodityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 商品发布相关接口
 */

@RestController
@RequestMapping("commodity")
@Tag(name = "商品发布相关接口")
public class CommodityController {
    @Resource
    private TCommodityService commodityService;

    @Resource
    private CommodityPictureService commodityPictureService;

    @Autowired
    private HttpServletRequest servletRequest;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private StringRedisTemplate redisTemplate;



    // 设置上传地址
    @Value("${upload.path}")
    String updateFilePath;

    /**
     * 商品详情图上传
     * @param file 上传文件
     * @return 文件访问地址
     */
    @PostMapping("picture")
    @Operation(summary = "商品详情展示图片上传接口")
    public HashMap<String, Object> commodityPictureSave(@RequestParam("file") MultipartFile file)
    {
        // 拼接存储地址
        String filePath = updateFilePath + "/upload";

        // 文件存储
        String path = commodityPictureService.commodityDetailsPicture(filePath, file);

        // 存储到redis服务器中
//        redisTemplate.opsForList().rightPush("人才", path);


        HashMap<String, String> mapData = new HashMap<>();
        mapData.put("url", "upload/" + path);

        HashMap<String, Object> map = new HashMap<>();
        map.put("errno", 0);
        map.put("data", mapData);


        return map;
    }


    /**
     * 商品主图上传
     * @param fileList 商品主图列表
     * @return 访问地址
     */
    @PostMapping("pictures")
    @Operation(summary = "商品主图上传接口")
    public ResultJsonData<ArrayList<String>>  commodityPictureSaves(@RequestParam("files") List<MultipartFile> fileList)
    {
        // 拼接存储地址
        String filePath = updateFilePath + "/upload";

        ArrayList<String> picturePaths = commodityPictureService.commodityPictureAdd(filePath, fileList);


        return ResultJsonData.success(picturePaths);
    }


    /**
     * 商品基本信息和详情信息存储
     * @param commodityVO 信息列表
     * @return OK
     */
    @PostMapping("info")
    @Operation(summary = "商品信息上传接口")
    public ResultJsonData<Void> commodityInfoSave(@Valid @RequestBody CommodityVO commodityVO)
    {
        String token = servletRequest.getHeader("token");
        TokenInfo info = tokenUtil.getTokenTuser(token);

        commodityService.CommodityAdd(commodityVO, info);

        return ResultJsonData.success();
    }

    /**
     *  根据用户id条件查询获取商品列表信息
     * @param commodity 分页条件参数
     * @return 分页商品信息
     */
    @GetMapping("/select")
    @Operation(summary = "根据用户id条件查询获取商品列表信息")
    public ResultJsonData<PageDOT> getByUserIdCommodity(@Valid commodityPageQueryVO commodity)
    {
        String token = servletRequest.getHeader("token");
        TokenInfo info = tokenUtil.getTokenTuser(token);

        PageDOT dot = commodityService.getByIdCommodityPageQuery(commodity, info.getUId());

        return  ResultJsonData.success(dot);
    }

    @GetMapping("{id}")
    @Operation(summary = "根据商品id获取到商品的详情信息")
    public ResultJsonData<CommodityDTO> getByIdCommodityInfo(@PathVariable("id") Integer id){
        CommodityDTO dto = commodityService.getByIdCommodity(id);
        return ResultJsonData.success(dto);

    }








}
