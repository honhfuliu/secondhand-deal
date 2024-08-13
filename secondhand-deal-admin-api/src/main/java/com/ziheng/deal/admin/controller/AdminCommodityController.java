package com.ziheng.deal.admin.controller;

import com.ziheng.deal.common.domain.DTO.CommodityDTO;
import com.ziheng.deal.common.domain.DTO.PageDOT;
import com.ziheng.deal.common.domain.DTO.commodityListDTO;
import com.ziheng.deal.common.domain.TokenInfo;
import com.ziheng.deal.common.domain.VO.CommodityVerifyVO;
import com.ziheng.deal.common.domain.query.PageQueryBase;
import com.ziheng.deal.common.resp.ResultJsonData;
import com.ziheng.deal.common.util.TokenUtil;
import com.ziheng.deal.db.service.TCommodityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("adminCommodity")
@Tag(name = "商品审核接口")
public class AdminCommodityController {
    @Resource
    private TCommodityService commodityService;

    @Autowired
    private HttpServletRequest servletRequest;

    @Autowired
    private TokenUtil tokenUtil;


    @GetMapping("all")
    @Operation(summary = "分页获取审核商品")
    public ResultJsonData<PageDOT<commodityListDTO>> QueryGetPaginationCommodity(@Valid PageQueryBase pageQueryBase){
        PageDOT<commodityListDTO> dot = commodityService.queryGetPaginationCommodity(pageQueryBase);
        return ResultJsonData.success(dot);
    }

    @GetMapping("{id}")
    @Operation(summary = "根据id获取到未审核商品的详细信息")
    public ResultJsonData<CommodityDTO> getByIdDetailsCommodity(@PathVariable("id") Integer id){
        CommodityDTO dto = commodityService.getByIdDetailsCommodity(id);
        return ResultJsonData.success(dto);
    }


    /**
     * 商品审核提交
     * @param commodityVerifyVO 审核数据
     * @return OK
     */
    @PostMapping("/v")
    @Operation(summary = "商品审核提交")
    public ResultJsonData<Void> getByIdVerifyCommoditySubmit(@Valid @RequestBody CommodityVerifyVO commodityVerifyVO){
        String token = servletRequest.getHeader("token");
        TokenInfo info = tokenUtil.getTokenTuser(token);
        commodityService.getByIdVerifyCommoditySbumit(commodityVerifyVO, info.getUsername());
        return ResultJsonData.success();
    }
}
