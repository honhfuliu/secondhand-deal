package com.ziheng.deal.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName t_log
 */
@TableName(value ="t_log")
public class TLog implements Serializable {
    /**
     * 日志id主键自增
     */
    @TableId(type = IdType.AUTO)
    private Integer lId;

    /**
     * 日志创建时间
     */
    private Date createTime;

    /**
     * 备注或描述信息
     */
    private String remarks;

    /**
     * 记录更新的时间
     */
    private Date updateTime;

    /**
     * 请求的地区信息
     */
    private String region;

    /**
     * 客户端使用的浏览器信息
     */
    private String browser;

    /**
     * 请求或日志涉及的城市信息
     */
    private String city;

    /**
     * 记录的类名和方法名
     */
    private String classMethod;

    /**
     * 异常信息，如发生的错误
     */
    private String exception;

    /**
     * http请求方法，如get， post
     */
    private String httpMethod;

    /**
     * 请求设计的网络服务提供商信息
     */
    private String isp;

    /**
     * 请求或日志涉及的省份信息
     */
    private String pro;

    /**
     * 请求的远程ip地址
     */
    private String remoteAddr;

    /**
     * 请求的URL
     */
    private String requestUrl;

    /**
     * 记录请求的类型，如日志类型或请求类型
     */
    private String type;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public Integer getlId() {
        return lId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public String getRegion() {
        return region;
    }

    public String getBrowser() {
        return browser;
    }

    public String getCity() {
        return city;
    }

    public String getClassMethod() {
        return classMethod;
    }

    public String getException() {
        return exception;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public String getIsp() {
        return isp;
    }

    public String getPro() {
        return pro;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public String getType() {
        return type;
    }

    public void setlId(Integer lId) {
        this.lId = lId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setClassMethod(String classMethod) {
        this.classMethod = classMethod;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public void setPro(String pro) {
        this.pro = pro;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public void setType(String type) {
        this.type = type;
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
        TLog other = (TLog) that;
        return (this.getlId() == null ? other.getlId() == null : this.getlId().equals(other.getlId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getRemarks() == null ? other.getRemarks() == null : this.getRemarks().equals(other.getRemarks()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getRegion() == null ? other.getRegion() == null : this.getRegion().equals(other.getRegion()))
            && (this.getBrowser() == null ? other.getBrowser() == null : this.getBrowser().equals(other.getBrowser()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
            && (this.getClassMethod() == null ? other.getClassMethod() == null : this.getClassMethod().equals(other.getClassMethod()))
            && (this.getException() == null ? other.getException() == null : this.getException().equals(other.getException()))
            && (this.getHttpMethod() == null ? other.getHttpMethod() == null : this.getHttpMethod().equals(other.getHttpMethod()))
            && (this.getIsp() == null ? other.getIsp() == null : this.getIsp().equals(other.getIsp()))
            && (this.getPro() == null ? other.getPro() == null : this.getPro().equals(other.getPro()))
            && (this.getRemoteAddr() == null ? other.getRemoteAddr() == null : this.getRemoteAddr().equals(other.getRemoteAddr()))
            && (this.getRequestUrl() == null ? other.getRequestUrl() == null : this.getRequestUrl().equals(other.getRequestUrl()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getlId() == null) ? 0 : getlId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getRemarks() == null) ? 0 : getRemarks().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getRegion() == null) ? 0 : getRegion().hashCode());
        result = prime * result + ((getBrowser() == null) ? 0 : getBrowser().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getClassMethod() == null) ? 0 : getClassMethod().hashCode());
        result = prime * result + ((getException() == null) ? 0 : getException().hashCode());
        result = prime * result + ((getHttpMethod() == null) ? 0 : getHttpMethod().hashCode());
        result = prime * result + ((getIsp() == null) ? 0 : getIsp().hashCode());
        result = prime * result + ((getPro() == null) ? 0 : getPro().hashCode());
        result = prime * result + ((getRemoteAddr() == null) ? 0 : getRemoteAddr().hashCode());
        result = prime * result + ((getRequestUrl() == null) ? 0 : getRequestUrl().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", lId=").append(lId);
        sb.append(", createTime=").append(createTime);
        sb.append(", remarks=").append(remarks);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", area=").append(region);
        sb.append(", browser=").append(browser);
        sb.append(", city=").append(city);
        sb.append(", classMethod=").append(classMethod);
        sb.append(", exception=").append(exception);
        sb.append(", httpMethod=").append(httpMethod);
        sb.append(", isp=").append(isp);
        sb.append(", province=").append(pro);
        sb.append(", remoteAddr=").append(remoteAddr);
        sb.append(", requestUrl=").append(requestUrl);
        sb.append(", type=").append(type);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}