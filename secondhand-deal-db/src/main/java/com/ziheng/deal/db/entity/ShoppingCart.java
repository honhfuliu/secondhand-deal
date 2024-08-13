package com.ziheng.deal.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 购物车表
 * @TableName shopping_cart
 */
@TableName(value ="shopping_cart")
@Data
public class ShoppingCart implements Serializable {
    /**
     * 购物车id主键自增
     */
    @TableId(type = IdType.AUTO)
    private Integer shoppingId;

    /**
     * 商品数量
     */
    private Integer shoppingNumber;

    /**
     * 购物车商品状态0未删除 1删除
     */
    private Integer shoppingStatus;

    /**
     * 购物车商品的价格
     */
    private BigDecimal shoppingPrice;

    /**
     * 加入购物车时间
     */
    private Date shoppingTime;

    /**
     * 购物车最后一次修改记录
     */
    private Date shoppingUpdate;

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
        ShoppingCart other = (ShoppingCart) that;
        return (this.getShoppingId() == null ? other.getShoppingId() == null : this.getShoppingId().equals(other.getShoppingId()))
            && (this.getShoppingNumber() == null ? other.getShoppingNumber() == null : this.getShoppingNumber().equals(other.getShoppingNumber()))
            && (this.getShoppingStatus() == null ? other.getShoppingStatus() == null : this.getShoppingStatus().equals(other.getShoppingStatus()))
            && (this.getShoppingPrice() == null ? other.getShoppingPrice() == null : this.getShoppingPrice().equals(other.getShoppingPrice()))
            && (this.getShoppingTime() == null ? other.getShoppingTime() == null : this.getShoppingTime().equals(other.getShoppingTime()))
            && (this.getShoppingUpdate() == null ? other.getShoppingUpdate() == null : this.getShoppingUpdate().equals(other.getShoppingUpdate()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getCommodityId() == null ? other.getCommodityId() == null : this.getCommodityId().equals(other.getCommodityId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getShoppingId() == null) ? 0 : getShoppingId().hashCode());
        result = prime * result + ((getShoppingNumber() == null) ? 0 : getShoppingNumber().hashCode());
        result = prime * result + ((getShoppingStatus() == null) ? 0 : getShoppingStatus().hashCode());
        result = prime * result + ((getShoppingPrice() == null) ? 0 : getShoppingPrice().hashCode());
        result = prime * result + ((getShoppingTime() == null) ? 0 : getShoppingTime().hashCode());
        result = prime * result + ((getShoppingUpdate() == null) ? 0 : getShoppingUpdate().hashCode());
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
        sb.append(", shoppingId=").append(shoppingId);
        sb.append(", shoppingNumber=").append(shoppingNumber);
        sb.append(", shoppingStatus=").append(shoppingStatus);
        sb.append(", shoppingPrice=").append(shoppingPrice);
        sb.append(", shoppingTime=").append(shoppingTime);
        sb.append(", shoppingUpdate=").append(shoppingUpdate);
        sb.append(", userId=").append(userId);
        sb.append(", commodityId=").append(commodityId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}