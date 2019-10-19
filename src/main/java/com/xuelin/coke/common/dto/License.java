package com.xuelin.coke.common.dto;

import java.util.Date;

/**
 * Created with Intellij IDEA
 * User: dongguang.hu
 * Date: 2019年01月28日 15:38
 */
public class License {

    private String licenseID;

    private String macAddress; //mac地址

    private String companyName; //公司名称

    private String appName; //应用名称

    private String extendInfo; //扩展字段

    private Date iat;//签发日期

    private Date exp;//过期时间


    public String getLicenseID() {
        return licenseID;
    }

    public void setLicenseID(String licenseID) {
        this.licenseID = licenseID;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getExtendInfo() {
        return extendInfo;
    }

    public void setExtendInfo(String extendInfo) {
        this.extendInfo = extendInfo;
    }

    public Date getIat() {
        return iat;
    }

    public void setIat(Date iat) {
        this.iat = iat;
    }

    public Date getExp() {
        return exp;
    }

    public void setExp(Date exp) {
        this.exp = exp;
    }
}
