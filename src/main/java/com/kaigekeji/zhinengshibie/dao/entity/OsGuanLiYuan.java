package com.kaigekeji.zhinengshibie.dao.entity;

import java.io.Serializable;

public class OsGuanLiYuan implements Serializable {
    private String bianHao;

    private String yongHuMing;

    private String miMa;

    private static final long serialVersionUID = 1L;

    public String getBianHao() {
        return bianHao;
    }

    public void setBianHao(String bianHao) {
        this.bianHao = bianHao;
    }

    public String getYongHuMing() {
        return yongHuMing;
    }

    public void setYongHuMing(String yongHuMing) {
        this.yongHuMing = yongHuMing;
    }

    public String getMiMa() {
        return miMa;
    }

    public void setMiMa(String miMa) {
        this.miMa = miMa;
    }

    @Override
    public String toString() {
        return "OsGuanLiYuan{" +
                "bianHao='" + bianHao + '\'' +
                ", yongHuMing='" + yongHuMing + '\'' +
                ", miMa='" + miMa + '\'' +
                '}';
    }
}