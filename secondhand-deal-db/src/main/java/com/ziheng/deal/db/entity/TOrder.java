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
 * 订单表
 * @TableName t_order
 */
@TableName(value ="t_order")
@Data
public class TOrder implements Serializable {
    /**
     * 订单id （主键 自增）
     */
    @TableId(type = IdType.AUTO)
    private Integer orderId;

    /**
     * 订单号 （索引）
     */
    private String orderNumber;

    /**
     * 卖家id （外键 索引）
     */
    private Integer sellerId;

    /**
     * 买家id (外键 索引)
     */
    private Integer buyerId;

    /**
     * 商品id （外键 索引）
     */
    private Integer commodityId;

    /**
     * 买家地址id（外键 索引）
     */
    private Integer addressId;

    /**
     * 商品skuId (商品sku外键)
     */
    private Integer skuId;

    /**
     * 订单商品序号
     */
    private Integer sort;

    /**
     * 商品数量
     */
    private Integer commodityNumber;

    /**
     * 商品单价
     */
    private BigDecimal commodityUnitPrice;

    /**
     * 是否使用积分
     */
    private Object whetherIntegral;

    /**
     * 积分优惠金额
     */
    private BigDecimal discountedPrice;

    /**
     * 订单金额
     */
    private BigDecimal orderPrice;

    /**
     * 订单状态 0-待付款，1-已付款，2-已发货，3-已收货，4-已完成，5-已退款，6-已取消
     */
    private Object orderStatus;

    /**
     * 配送方式：上门自取 配送上门
     */
    private Object deliveryMethod;

    /**
     * 配送费用
     */
    private BigDecimal deliveryFee;

    /**
     * 订单创建时间
     */
    private Date orderStartTime;

    /**
     * 付款时间
     */
    private Date paymentTime;

    /**
     * 发货时间
     */
    private Date shipTime;

    /**
     * 收货时间
     */
    private Date receiptGoodsTime;

    /**
     * 订单结束时间
     */
    private Date orderEndTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

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
        TOrder other = (TOrder) that;
        return (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getOrderNumber() == null ? other.getOrderNumber() == null : this.getOrderNumber().equals(other.getOrderNumber()))
            && (this.getSellerId() == null ? other.getSellerId() == null : this.getSellerId().equals(other.getSellerId()))
            && (this.getBuyerId() == null ? other.getBuyerId() == null : this.getBuyerId().equals(other.getBuyerId()))
            && (this.getCommodityId() == null ? other.getCommodityId() == null : this.getCommodityId().equals(other.getCommodityId()))
            && (this.getAddressId() == null ? other.getAddressId() == null : this.getAddressId().equals(other.getAddressId()))
            && (this.getCommodityNumber() == null ? other.getCommodityNumber() == null : this.getCommodityNumber().equals(other.getCommodityNumber()))
            && (this.getCommodityUnitPrice() == null ? other.getCommodityUnitPrice() == null : this.getCommodityUnitPrice().equals(other.getCommodityUnitPrice()))
            && (this.getDiscountedPrice() == null ? other.getDiscountedPrice() == null : this.getDiscountedPrice().equals(other.getDiscountedPrice()))
            && (this.getOrderPrice() == null ? other.getOrderPrice() == null : this.getOrderPrice().equals(other.getOrderPrice()))
            && (this.getOrderStatus() == null ? other.getOrderStatus() == null : this.getOrderStatus().equals(other.getOrderStatus()))
            && (this.getDeliveryMethod() == null ? other.getDeliveryMethod() == null : this.getDeliveryMethod().equals(other.getDeliveryMethod()))
            && (this.getDeliveryFee() == null ? other.getDeliveryFee() == null : this.getDeliveryFee().equals(other.getDeliveryFee()))
            && (this.getOrderStartTime() == null ? other.getOrderStartTime() == null : this.getOrderStartTime().equals(other.getOrderStartTime()))
            && (this.getPaymentTime() == null ? other.getPaymentTime() == null : this.getPaymentTime().equals(other.getPaymentTime()))
            && (this.getShipTime() == null ? other.getShipTime() == null : this.getShipTime().equals(other.getShipTime()))
            && (this.getReceiptGoodsTime() == null ? other.getReceiptGoodsTime() == null : this.getReceiptGoodsTime().equals(other.getReceiptGoodsTime()))
            && (this.getOrderEndTime() == null ? other.getOrderEndTime() == null : this.getOrderEndTime().equals(other.getOrderEndTime()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getOrderNumber() == null) ? 0 : getOrderNumber().hashCode());
        result = prime * result + ((getSellerId() == null) ? 0 : getSellerId().hashCode());
        result = prime * result + ((getBuyerId() == null) ? 0 : getBuyerId().hashCode());
        result = prime * result + ((getCommodityId() == null) ? 0 : getCommodityId().hashCode());
        result = prime * result + ((getAddressId() == null) ? 0 : getAddressId().hashCode());
        result = prime * result + ((getCommodityNumber() == null) ? 0 : getCommodityNumber().hashCode());
        result = prime * result + ((getCommodityUnitPrice() == null) ? 0 : getCommodityUnitPrice().hashCode());
        result = prime * result + ((getDiscountedPrice() == null) ? 0 : getDiscountedPrice().hashCode());
        result = prime * result + ((getOrderPrice() == null) ? 0 : getOrderPrice().hashCode());
        result = prime * result + ((getOrderStatus() == null) ? 0 : getOrderStatus().hashCode());
        result = prime * result + ((getDeliveryMethod() == null) ? 0 : getDeliveryMethod().hashCode());
        result = prime * result + ((getDeliveryFee() == null) ? 0 : getDeliveryFee().hashCode());
        result = prime * result + ((getOrderStartTime() == null) ? 0 : getOrderStartTime().hashCode());
        result = prime * result + ((getPaymentTime() == null) ? 0 : getPaymentTime().hashCode());
        result = prime * result + ((getShipTime() == null) ? 0 : getShipTime().hashCode());
        result = prime * result + ((getReceiptGoodsTime() == null) ? 0 : getReceiptGoodsTime().hashCode());
        result = prime * result + ((getOrderEndTime() == null) ? 0 : getOrderEndTime().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderId=").append(orderId);
        sb.append(", orderNumber=").append(orderNumber);
        sb.append(", sellerId=").append(sellerId);
        sb.append(", buyerId=").append(buyerId);
        sb.append(", commodityId=").append(commodityId);
        sb.append(", addressId=").append(addressId);
        sb.append(", commodityNumber=").append(commodityNumber);
        sb.append(", commodityUnitPrice=").append(commodityUnitPrice);
        sb.append(", discountedPrice=").append(discountedPrice);
        sb.append(", orderPrice=").append(orderPrice);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", deliveryMethod=").append(deliveryMethod);
        sb.append(", deliveryFee=").append(deliveryFee);
        sb.append(", orderStartTime=").append(orderStartTime);
        sb.append(", paymentTime=").append(paymentTime);
        sb.append(", shipTime=").append(shipTime);
        sb.append(", receiptGoodsTime=").append(receiptGoodsTime);
        sb.append(", orderEndTime=").append(orderEndTime);
        sb.append(", createUser=").append(createUser);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}