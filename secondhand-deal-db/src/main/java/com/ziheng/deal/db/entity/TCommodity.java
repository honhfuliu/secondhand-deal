package com.ziheng.deal.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 商品表
 * @TableName t_commodity
 */
@TableName(value ="t_commodity")

public class TCommodity extends BaseEntity implements Serializable {
    /**
     * 商品id主键自增
     */
    @TableId(type = IdType.AUTO)
    private Integer cId;

    /**
     * 商品标题
     */
    private String commodityTitle;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 产地
     */
    private String locality;

    /**
     * 是否整装（是 否）
     */
    private String isinteger;

    /**
     * 风格
     */
    private String style;

    /**
     * 图案
     */
    private String design;

    /**
     * 材质
     */
    private String material;

    /**
     * 适用对象
     */
    private String suitableObject;

    /**
     * 商品类型（成色）
     */
    private String commodityType;

    /**
     * 商品价格
     */
    private BigDecimal cPrice;

    /**
     * 商品库存
     */
    private Integer cNumber;

    /**
     * 配送方式
     */
    private String deliveryMethod;

    /**
     * 配送费用
     */
    private BigDecimal shippingFees;

    /**
     * 商品详情信息
     */
    private String commodityDetails;

    /**
     * 商品状态 0-待审核，1-驳回 2-上架，3-下架
     */
    private Integer cStatus;

    /**
     * 商品审核不通过原因
     */
    private String rejectReason;

    /**
     * 销量
     */
    private Integer buyNumber;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private String modifiedUser;

    /**
     * 修改时间
     */
    private Date modifiedTime;

    /**
     * 商品分类id 外键
     */
    private Integer classifyId;

    /**
     * 卖家id 外键
     */
    private Integer userId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public TCommodity(Integer cId, String commodityTitle, String brand, String locality, String isinteger, String style, String design, String material, String suitableObject, String commodityType, BigDecimal cPrice, Integer cNumber, String deliveryMethod, BigDecimal shippingFees, String commodityDetails, Integer cStatus, String rejectReason, Integer buyNumber, String createUser, Date createTime, String modifiedUser, Date modifiedTime, Integer classifyId, Integer userId) {
        this.cId = cId;
        this.commodityTitle = commodityTitle;
        this.brand = brand;
        this.locality = locality;
        this.isinteger = isinteger;
        this.style = style;
        this.design = design;
        this.material = material;
        this.suitableObject = suitableObject;
        this.commodityType = commodityType;
        this.cPrice = cPrice;
        this.cNumber = cNumber;
        this.deliveryMethod = deliveryMethod;
        this.shippingFees = shippingFees;
        this.commodityDetails = commodityDetails;
        this.cStatus = cStatus;
        this.rejectReason = rejectReason;
        this.buyNumber = buyNumber;
        this.createUser = createUser;
        this.createTime = createTime;
        this.modifiedUser = modifiedUser;
        this.modifiedTime = modifiedTime;
        this.classifyId = classifyId;
        this.userId = userId;
    }

    public TCommodity() {
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public Integer getBuyNumber() {
        return buyNumber;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }

    public Integer getcId() {
        return cId;
    }

    public String getCommodityTitle() {
        return commodityTitle;
    }

    public String getBrand() {
        return brand;
    }

    public String getLocality() {
        return locality;
    }

    public String getIsinteger() {
        return isinteger;
    }

    public String getStyle() {
        return style;
    }

    public String getDesign() {
        return design;
    }

    public String getMaterial() {
        return material;
    }

    public String getSuitableObject() {
        return suitableObject;
    }

    public String getCommodityType() {
        return commodityType;
    }

    public BigDecimal getcPrice() {
        return cPrice;
    }

    public Integer getcNumber() {
        return cNumber;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public BigDecimal getShippingFees() {
        return shippingFees;
    }

    public String getCommodityDetails() {
        return commodityDetails;
    }

    public Integer getcStatus() {
        return cStatus;
    }

    @Override
    public String getCreateUser() {
        return createUser;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public String getModifiedUser() {
        return modifiedUser;
    }

    @Override
    public Date getModifiedTime() {
        return modifiedTime;
    }

    public Integer getClassifyId() {
        return classifyId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public void setCommodityTitle(String commodityTitle) {
        this.commodityTitle = commodityTitle;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public void setIsinteger(String isinteger) {
        this.isinteger = isinteger;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setSuitableObject(String suitableObject) {
        this.suitableObject = suitableObject;
    }

    public void setCommodityType(String commodityType) {
        this.commodityType = commodityType;
    }

    public void setcPrice(BigDecimal cPrice) {
        this.cPrice = cPrice;
    }

    public void setcNumber(Integer cNumber) {
        this.cNumber = cNumber;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public void setShippingFees(BigDecimal shippingFees) {
        this.shippingFees = shippingFees;
    }

    public void setCommodityDetails(String commodityDetails) {
        this.commodityDetails = commodityDetails;
    }

    public void setcStatus(Integer cStatus) {
        this.cStatus = cStatus;
    }

    @Override
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    @Override
    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
        TCommodity other = (TCommodity) that;
        return (this.getcId() == null ? other.getcId() == null : this.getcId().equals(other.getcId()))
            && (this.getCommodityTitle() == null ? other.getCommodityTitle() == null : this.getCommodityTitle().equals(other.getCommodityTitle()))
            && (this.getBrand() == null ? other.getBrand() == null : this.getBrand().equals(other.getBrand()))
            && (this.getLocality() == null ? other.getLocality() == null : this.getLocality().equals(other.getLocality()))
            && (this.getIsinteger() == null ? other.getIsinteger() == null : this.getIsinteger().equals(other.getIsinteger()))
            && (this.getStyle() == null ? other.getStyle() == null : this.getStyle().equals(other.getStyle()))
            && (this.getDesign() == null ? other.getDesign() == null : this.getDesign().equals(other.getDesign()))
            && (this.getMaterial() == null ? other.getMaterial() == null : this.getMaterial().equals(other.getMaterial()))
            && (this.getSuitableObject() == null ? other.getSuitableObject() == null : this.getSuitableObject().equals(other.getSuitableObject()))
            && (this.getCommodityType() == null ? other.getCommodityType() == null : this.getCommodityType().equals(other.getCommodityType()))
            && (this.getcPrice() == null ? other.getcPrice() == null : this.getcPrice().equals(other.getcPrice()))
            && (this.getcNumber() == null ? other.getcNumber() == null : this.getcNumber().equals(other.getcNumber()))
            && (this.getDeliveryMethod() == null ? other.getDeliveryMethod() == null : this.getDeliveryMethod().equals(other.getDeliveryMethod()))
            && (this.getShippingFees() == null ? other.getShippingFees() == null : this.getShippingFees().equals(other.getShippingFees()))
            && (this.getCommodityDetails() == null ? other.getCommodityDetails() == null : this.getCommodityDetails().equals(other.getCommodityDetails()))
            && (this.getcStatus() == null ? other.getcStatus() == null : this.getcStatus().equals(other.getcStatus()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getModifiedUser() == null ? other.getModifiedUser() == null : this.getModifiedUser().equals(other.getModifiedUser()))
            && (this.getModifiedTime() == null ? other.getModifiedTime() == null : this.getModifiedTime().equals(other.getModifiedTime()))
            && (this.getClassifyId() == null ? other.getClassifyId() == null : this.getClassifyId().equals(other.getClassifyId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getcId() == null) ? 0 : getcId().hashCode());
        result = prime * result + ((getCommodityTitle() == null) ? 0 : getCommodityTitle().hashCode());
        result = prime * result + ((getBrand() == null) ? 0 : getBrand().hashCode());
        result = prime * result + ((getLocality() == null) ? 0 : getLocality().hashCode());
        result = prime * result + ((getIsinteger() == null) ? 0 : getIsinteger().hashCode());
        result = prime * result + ((getStyle() == null) ? 0 : getStyle().hashCode());
        result = prime * result + ((getDesign() == null) ? 0 : getDesign().hashCode());
        result = prime * result + ((getMaterial() == null) ? 0 : getMaterial().hashCode());
        result = prime * result + ((getSuitableObject() == null) ? 0 : getSuitableObject().hashCode());
        result = prime * result + ((getCommodityType() == null) ? 0 : getCommodityType().hashCode());
        result = prime * result + ((getcPrice() == null) ? 0 : getcPrice().hashCode());
        result = prime * result + ((getcNumber() == null) ? 0 : getcNumber().hashCode());
        result = prime * result + ((getDeliveryMethod() == null) ? 0 : getDeliveryMethod().hashCode());
        result = prime * result + ((getShippingFees() == null) ? 0 : getShippingFees().hashCode());
        result = prime * result + ((getCommodityDetails() == null) ? 0 : getCommodityDetails().hashCode());
        result = prime * result + ((getcStatus() == null) ? 0 : getcStatus().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getModifiedUser() == null) ? 0 : getModifiedUser().hashCode());
        result = prime * result + ((getModifiedTime() == null) ? 0 : getModifiedTime().hashCode());
        result = prime * result + ((getClassifyId() == null) ? 0 : getClassifyId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", cId=").append(cId);
        sb.append(", commodityTitle=").append(commodityTitle);
        sb.append(", brand=").append(brand);
        sb.append(", locality=").append(locality);
        sb.append(", isinteger=").append(isinteger);
        sb.append(", style=").append(style);
        sb.append(", design=").append(design);
        sb.append(", material=").append(material);
        sb.append(", suitableObject=").append(suitableObject);
        sb.append(", commodityType=").append(commodityType);
        sb.append(", cPrice=").append(cPrice);
        sb.append(", cNumber=").append(cNumber);
        sb.append(", deliveryMethod=").append(deliveryMethod);
        sb.append(", shippingFees=").append(shippingFees);
        sb.append(", commodityDetails=").append(commodityDetails);
        sb.append(", cStatus=").append(cStatus);
        sb.append(", createUser=").append(createUser);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifiedUser=").append(modifiedUser);
        sb.append(", modifiedTime=").append(modifiedTime);
        sb.append(", classifyId=").append(classifyId);
        sb.append(", userId=").append(userId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}