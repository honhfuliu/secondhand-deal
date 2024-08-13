package com.ziheng.deal.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName province
 */
@TableName(value ="province")
@Data
public class Province implements Serializable {
    /**
     * 区划代码
     */
    @TableId
    private Long code;

    /**
     * 名称
     */
    private String name;

    /**
     * 级别1-5,省市县镇村
     */
    private Integer level;

    /**
     * 父级区划代码
     */
    private Long pcode;

    /**
     * 城乡分类
     */
    private Integer category;

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
        Province other = (Province) that;
        return (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
            && (this.getPcode() == null ? other.getPcode() == null : this.getPcode().equals(other.getPcode()))
            && (this.getCategory() == null ? other.getCategory() == null : this.getCategory().equals(other.getCategory()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        result = prime * result + ((getPcode() == null) ? 0 : getPcode().hashCode());
        result = prime * result + ((getCategory() == null) ? 0 : getCategory().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", level=").append(level);
        sb.append(", pcode=").append(pcode);
        sb.append(", category=").append(category);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}