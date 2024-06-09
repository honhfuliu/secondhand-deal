package com.ziheng.deal.common.domain.DTO;

import lombok.Data;

import java.util.List;

/**
 * 商品分类返回
 */
@Data
public class ClassificationDTO {
    private Integer classificationId;

    private String classificationName;

    private Integer parentId;

    private Integer level;

    private Integer sort;

    private List<ClassificationDTO> Children;

    @Override
    public String toString() {
        return "ClassificationDTO{" +
                "classificationId=" + classificationId +
                ", classificationName='" + classificationName + '\'' +
                ", parentId=" + parentId +
                ", level=" + level +
                ", sort=" + sort +
                ", Children=" + Children +
                '}';
    }
}
