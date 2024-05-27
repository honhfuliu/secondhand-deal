package com.ziheng.deal.admin.controller;

import com.ziheng.deal.common.domain.TokenInfo;
import com.ziheng.deal.common.resp.ResultJsonData;
import com.ziheng.deal.common.util.TokenUtil;
import com.ziheng.deal.db.domain.DTO.ClassificationDTO;
import com.ziheng.deal.db.entity.TClassification;
import com.ziheng.deal.db.service.TClassificationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ification")
@Tag(name = "商品分类模块")
public class ClassificationController {
    @Resource
    private TClassificationService classificationService;

    @Resource
    private HttpServletRequest servletRequest;

    @Resource
    private TokenUtil tokenUtil;


    /**
     * 查询所有商品分类
     * @return 所有商品分类
     */
    @GetMapping()
    @Operation(summary = "查询所有商品分类")
    public ResultJsonData<List<ClassificationDTO>> getClassificationAll(){
        List<ClassificationDTO> dtos = classificationService.getAllClassification();
        return ResultJsonData.success(dtos);

    }

    /**
     * 查询前两级商品分类
     * @return 前两级商品分类
     */
    @GetMapping("/tow")
    @Operation(summary = "查询前两级商品分类")
    public ResultJsonData<List<ClassificationDTO>> GetClassificationTow(){
        List<ClassificationDTO> dtos = classificationService.GetClassificationTow();
        return ResultJsonData.success(dtos);

    }

    /**
     * 分类添加
     * @param tClassification 分类数据对象
     * @return OK
     */
    @PostMapping()
    @Operation(summary = "商品分类添加")
    public ResultJsonData<Void> addClassification(@RequestBody TClassification tClassification){
        String token = servletRequest.getHeader("token");
        TokenInfo tokenTuser = tokenUtil.getTokenTuser(token);

        classificationService.AddClassification(tClassification, tokenTuser.getUsername());
        return ResultJsonData.success();
    }


    /**
     * 根据每个商品分类的id查询出所有上级分类
     * @param id
     * @return 分类对象
     */
    @GetMapping("/{id}")
    @Operation(summary = "根据每个商品分类的id查询出所有上级分类")
    public ResultJsonData<ClassificationDTO> getByIdClassification(@PathVariable("id") Integer id){
        ClassificationDTO byIdClassificationInfo = classificationService.getByIdClassificationInfo(id);
        return ResultJsonData.success(byIdClassificationInfo);
    }

    @PutMapping("")
    @Operation(summary = "根据id修改商品分类")
    public ResultJsonData<Void> getByIdClassification(@RequestBody TClassification tClassification) {
        String token = servletRequest.getHeader("token");
        TokenInfo tokenTuser = tokenUtil.getTokenTuser(token);
        classificationService.getByIdUpdateClassification(tClassification, tokenTuser.getUsername());
        return ResultJsonData.success();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "根据id删除商品分类")
    public ResultJsonData<Void> getByIdDeleteClassification(@PathVariable("id") Integer id){
        classificationService.getByIdDeleteClassification(id);
        return ResultJsonData.success();
    }
}
