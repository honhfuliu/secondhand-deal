package com.ziheng.deal.db.domain.DTO;

import lombok.Data;

import java.util.List;

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
