package com.ziheng.deal.controller;

import com.ziheng.deal.common.domain.DTO.CollectCommodityDTO;
import com.ziheng.deal.common.domain.TokenInfo;
import com.ziheng.deal.common.resp.ResultJsonData;
import com.ziheng.deal.common.util.TokenUtil;
import com.ziheng.deal.db.service.CommodityCollectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("collect")
@Tag(name = "商品收藏相关接口")
public class CommodityCollectController {
    @Resource
    private HttpServletRequest servletRequest;

    @Resource
    private CommodityCollectService commodityCollectService;

    @Resource
    private TokenUtil tokenUtil;

    /**
     * 根据用户id和商品id添加到收藏
     * @param payload 商品id
     * @return OK
     */
    @PostMapping()
    @Operation(summary = "商品收藏接口")
    public ResultJsonData<Void> CollectCommodity(@RequestBody Map<String, Integer> payload){
        String token = servletRequest.getHeader("token");
        TokenInfo info = tokenUtil.getTokenTuser(token);
        commodityCollectService.getByUserIdCollectCommodity(info.getUId(), payload.get("commodityId"));
        return ResultJsonData.success();
    }

    @GetMapping()
    @Operation(summary = "根据用户id获取到用户收藏商品")
    public ResultJsonData<List<CollectCommodityDTO>> getByUserIdCollectCommodity(){
        String token = servletRequest.getHeader("token");
        TokenInfo info = tokenUtil.getTokenTuser(token);
        List<CollectCommodityDTO> dtoList = commodityCollectService.getByUserIdCollectCommodity(info.getUId());
        return ResultJsonData.success(dtoList);
    }

    @PostMapping("/d")
    @Operation(summary = "删除收藏商品")
    public ResultJsonData<Void> getByCollectIdDelete(@RequestBody Map<String, Integer> payload){
        String token = servletRequest.getHeader("token");
        TokenInfo info = tokenUtil.getTokenTuser(token);
        commodityCollectService.getByCollectIdDelete(info.getUId(), payload.get("collectId"));
        return ResultJsonData.success();
    }

}
