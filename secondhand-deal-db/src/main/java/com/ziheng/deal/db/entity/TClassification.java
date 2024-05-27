package com.ziheng.deal.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 
 * @TableName t_classification
 */
@TableName(value ="t_classification")
@Data
public class TClassification extends BaseEntity implements Serializable {
    /**
     * 分类id主键自增
     */
    @TableId(type = IdType.AUTO)
    private Integer classificationId;

    /**
     * 分类名称
     */
    private String classificationName;

    /**
     * 级分类ID，0表示顶级分类， 父级的id
     */
    private Integer parentId;

    /**
     * 存储当前分类的级别，1表示一级分类，2表示二级分类，以此类推
     */
    private Integer level;

    /**
     * 存储当前分类的排序号，用于控制商品分类的显示顺序
     */
    private Integer sort;



    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TClassification other = (TClassification) that;
        return (this.getClassificationId() == null ? other.getClassificationId() == null : this.getClassificationId().equals(other.getClassificationId()))
            && (this.getClassificationName() == null ? other.getClassificationName() == null : this.getClassificationName().equals(other.getClassificationName()))
            && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
            && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getModifiedUser() == null ? other.getModifiedUser() == null : this.getModifiedUser().equals(other.getModifiedUser()))
            && (this.getModifiedTime() == null ? other.getModifiedTime() == null : this.getModifiedTime().equals(other.getModifiedTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getClassificationId() == null) ? 0 : getClassificationId().hashCode());
        result = prime * result + ((getClassificationName() == null) ? 0 : getClassificationName().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getModifiedUser() == null) ? 0 : getModifiedUser().hashCode());
        result = prime * result + ((getModifiedTime() == null) ? 0 : getModifiedTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", classificationId=").append(classificationId);
        sb.append(", classificationName=").append(classificationName);
        sb.append(", parentId=").append(parentId);
        sb.append(", level=").append(level);
        sb.append(", sort=").append(sort);
        sb.append(", createUser=").append(getCreateUser());
        sb.append(", createTime=").append(getCreateTime());
        sb.append(", modifiedUser=").append(getModifiedUser());
        sb.append(", modifiedTime=").append(getModifiedTime());
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}