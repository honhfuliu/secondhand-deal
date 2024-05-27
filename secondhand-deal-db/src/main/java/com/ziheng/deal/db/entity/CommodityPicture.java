package com.ziheng.deal.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 商品图片存储表
 * @TableName commodity_picture
 */
@TableName(value ="commodity_picture")
public class CommodityPicture implements Serializable {
    /**
     * 主键 自增 唯一
     */
    @TableId(type = IdType.AUTO)
    private Integer pId;

    /**
     * 商品图片路径
     */
    private String pPath;

    /**
     * 商品id外键
     */
    private Integer commodityId;

    /**
     * 是否是商品展示图（1是 0不是）
     */
    private Integer isDisplayPicture;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


    public CommodityPicture(Integer pId, String pPath, Integer commodityId, Integer isDisplayPicture) {
        this.pId = pId;
        this.pPath = pPath;
        this.commodityId = commodityId;
        this.isDisplayPicture = isDisplayPicture;
    }

    public CommodityPicture() {
    }

    public Integer getpId() {
        return pId;
    }

    public String getpPath() {
        return pPath;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public Integer getIsDisplayPicture() {
        return isDisplayPicture;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public void setpPath(String pPath) {
        this.pPath = pPath;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public void setIsDisplayPicture(Integer isDisplayPicture) {
        this.isDisplayPicture = isDisplayPicture;
    }

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
        CommodityPicture other = (CommodityPicture) that;
        return (this.getpId() == null ? other.getpId() == null : this.getpId().equals(other.getpId()))
            && (this.getpPath() == null ? other.getpPath() == null : this.getpPath().equals(other.getpPath()))
            && (this.getCommodityId() == null ? other.getCommodityId() == null : this.getCommodityId().equals(other.getCommodityId()))
            && (this.getIsDisplayPicture() == null ? other.getIsDisplayPicture() == null : this.getIsDisplayPicture().equals(other.getIsDisplayPicture()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getpId() == null) ? 0 : getpId().hashCode());
        result = prime * result + ((getpPath() == null) ? 0 : getpPath().hashCode());
        result = prime * result + ((getCommodityId() == null) ? 0 : getCommodityId().hashCode());
        result = prime * result + ((getIsDisplayPicture() == null) ? 0 : getIsDisplayPicture().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pId=").append(pId);
        sb.append(", pPath=").append(pPath);
        sb.append(", commodityId=").append(commodityId);
        sb.append(", isDisplayPicture=").append(isDisplayPicture);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}