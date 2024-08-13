package com.ziheng.deal.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 商品收藏表
 * @TableName commodity_collect
 */
@TableName(value ="commodity_collect")
@Data
public class CommodityCollect implements Serializable {
    /**
     * 收藏id主键自增
     */
    @TableId(type = IdType.AUTO)
    private Integer collectId;

    /**
     * 收藏商品状态0未删除 1删除
     */
    private Integer isDelete;

    /**
     * 收藏时间
     */
    private Date collectTime;

    /**
     * 外键收藏用户
     */
    private Integer userId;

    /**
     * 外键收藏商品
     */
    private Integer commodityId;

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
        CommodityCollect other = (CommodityCollect) that;
        return (this.getCollectId() == null ? other.getCollectId() == null : this.getCollectId().equals(other.getCollectId()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getCollectTime() == null ? other.getCollectTime() == null : this.getCollectTime().equals(other.getCollectTime()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getCommodityId() == null ? other.getCommodityId() == null : this.getCommodityId().equals(other.getCommodityId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCollectId() == null) ? 0 : getCollectId().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getCollectTime() == null) ? 0 : getCollectTime().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getCommodityId() == null) ? 0 : getCommodityId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", collectId=").append(collectId);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", collectTime=").append(collectTime);
        sb.append(", userId=").append(userId);
        sb.append(", commodityId=").append(commodityId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}