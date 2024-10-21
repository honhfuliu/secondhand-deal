package com.ziheng.deal.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 存储商品 SKU 及其相关信息的表
 * @TableName commodity_sku
 */
@TableName(value ="commodity_sku")
@Data
public class CommoditySku implements Serializable {
    /**
     * 主键，自增，唯一标识 SKUId
     */
    @TableId(type = IdType.AUTO)
    private Integer skuId;

    /**
     * 商品价格，精确到小数点后两位
     */
    private BigDecimal price;

    /**
     * 商品库存数量
     */
    private Integer commodityNumber;

    /**
     * 预警库存数量，当库存低于此值时发出警告
     */
    private Integer lowCommodityNumber;

    /**
     * 商品 SKU 的 JSON 字段，存储规格组合信息
     */
    private String commoditySku;



    /**
     * 外键，关联商品表的商品 ID
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
        CommoditySku other = (CommoditySku) that;
        return (this.getSkuId() == null ? other.getSkuId() == null : this.getSkuId().equals(other.getSkuId()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getCommodityNumber() == null ? other.getCommodityNumber() == null : this.getCommodityNumber().equals(other.getCommodityNumber()))
            && (this.getLowCommodityNumber() == null ? other.getLowCommodityNumber() == null : this.getLowCommodityNumber().equals(other.getLowCommodityNumber()))
            && (this.getCommoditySku() == null ? other.getCommoditySku() == null : this.getCommoditySku().equals(other.getCommoditySku()))
            && (this.getCommodityId() == null ? other.getCommodityId() == null : this.getCommodityId().equals(other.getCommodityId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSkuId() == null) ? 0 : getSkuId().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getCommodityNumber() == null) ? 0 : getCommodityNumber().hashCode());
        result = prime * result + ((getLowCommodityNumber() == null) ? 0 : getLowCommodityNumber().hashCode());
        result = prime * result + ((getCommoditySku() == null) ? 0 : getCommoditySku().hashCode());
        result = prime * result + ((getCommodityId() == null) ? 0 : getCommodityId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", skuId=").append(skuId);
        sb.append(", price=").append(price);
        sb.append(", commodityNumber=").append(commodityNumber);
        sb.append(", lowCommodityNumber=").append(lowCommodityNumber);
        sb.append(", commoditySku=").append(commoditySku);
        sb.append(", commodityId=").append(commodityId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}