package com.ziheng.deal.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName t_address
 */
@TableName(value ="t_address")
@Data
public class TAddress implements Serializable {
    /**
     * 收货地址ID 自增
     */
    @TableId(type = IdType.AUTO)
    private Integer addressId;

    /**
     * 用户ID 外键
     */
    private Integer userId;

    /**
     * 收货人名称
     */
    private String addressName;

    /**
     * 收货人手机号
     */
    private String contact;

    /**
     * 省
     */
    private Long province;

    /**
     * 市
     */
    private Long city;

    /**
     * 区
     */
    private Long area;

    /**
     * 街道
     */
    private Long street;

    /**
     * 详细地址
     */
    private String fullAddress;

    /**
     * 是否为默认地址
     */
    private Integer defaultAddress;

    /**
     * 日志-创建人
     */
    private String createUser;

    /**
     * 日志-创建时间
     */
    private Date createTime;

    /**
     * 日志-修改人
     */
    private String modifiedUser;

    /**
     * 日志-修改时间
     */
    private Date modifiedTime;

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
        TAddress other = (TAddress) that;
        return (this.getAddressId() == null ? other.getAddressId() == null : this.getAddressId().equals(other.getAddressId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getAddressName() == null ? other.getAddressName() == null : this.getAddressName().equals(other.getAddressName()))
            && (this.getContact() == null ? other.getContact() == null : this.getContact().equals(other.getContact()))
            && (this.getProvince() == null ? other.getProvince() == null : this.getProvince().equals(other.getProvince()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
            && (this.getArea() == null ? other.getArea() == null : this.getArea().equals(other.getArea()))
            && (this.getStreet() == null ? other.getStreet() == null : this.getStreet().equals(other.getStreet()))
            && (this.getFullAddress() == null ? other.getFullAddress() == null : this.getFullAddress().equals(other.getFullAddress()))
            && (this.getDefaultAddress() == null ? other.getDefaultAddress() == null : this.getDefaultAddress().equals(other.getDefaultAddress()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getModifiedUser() == null ? other.getModifiedUser() == null : this.getModifiedUser().equals(other.getModifiedUser()))
            && (this.getModifiedTime() == null ? other.getModifiedTime() == null : this.getModifiedTime().equals(other.getModifiedTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAddressId() == null) ? 0 : getAddressId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getAddressName() == null) ? 0 : getAddressName().hashCode());
        result = prime * result + ((getContact() == null) ? 0 : getContact().hashCode());
        result = prime * result + ((getProvince() == null) ? 0 : getProvince().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getArea() == null) ? 0 : getArea().hashCode());
        result = prime * result + ((getStreet() == null) ? 0 : getStreet().hashCode());
        result = prime * result + ((getFullAddress() == null) ? 0 : getFullAddress().hashCode());
        result = prime * result + ((getDefaultAddress() == null) ? 0 : getDefaultAddress().hashCode());
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
        sb.append(", addressId=").append(addressId);
        sb.append(", userId=").append(userId);
        sb.append(", addressName=").append(addressName);
        sb.append(", contact=").append(contact);
        sb.append(", province=").append(province);
        sb.append(", city=").append(city);
        sb.append(", area=").append(area);
        sb.append(", street=").append(street);
        sb.append(", fullAddress=").append(fullAddress);
        sb.append(", defaultAddress=").append(defaultAddress);
        sb.append(", createUser=").append(createUser);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifiedUser=").append(modifiedUser);
        sb.append(", modifiedTime=").append(modifiedTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}