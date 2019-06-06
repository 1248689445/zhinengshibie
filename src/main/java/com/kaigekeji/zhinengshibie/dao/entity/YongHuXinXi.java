package com.kaigekeji.zhinengshibie.dao.entity;

import java.io.Serializable;
import java.util.Date;

public class YongHuXinXi implements Serializable {
    private String bianHao;

    private String openId;

    private String niCheng;

    private String touXiang;

    private Date zhuCeShiJian;

    private Integer state;

    private Integer roleId;

    private static final long serialVersionUID = 1L;

    public String getBianHao() {
        return bianHao;
    }

    public void setBianHao(String bianHao) {
        this.bianHao = bianHao;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNiCheng() {
        return niCheng;
    }

    public void setNiCheng(String niCheng) {
        this.niCheng = niCheng;
    }

    public String getTouXiang() {
        return touXiang;
    }

    public void setTouXiang(String touXiang) {
        this.touXiang = touXiang;
    }

    public Date getZhuCeShiJian() {
        return zhuCeShiJian;
    }

    public void setZhuCeShiJian(Date zhuCeShiJian) {
        this.zhuCeShiJian = zhuCeShiJian;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer stats) {
        this.state = stats;
    }

    public Integer getRoleid() {
        return roleId;
    }

    public void setRoleid(Integer roleid) {
        this.roleId = roleid;
    }

    @Override
    public String toString() {
        return "YongHuXinXi{" +
                "bianHao='" + bianHao + '\'' +
                ", openId='" + openId + '\'' +
                ", niCheng='" + niCheng + '\'' +
                ", touXiang='" + touXiang + '\'' +
                ", zhuCeShiJian=" + zhuCeShiJian +
                ", state=" + state +
                ", roleId=" + roleId +
                '}';
    }
}