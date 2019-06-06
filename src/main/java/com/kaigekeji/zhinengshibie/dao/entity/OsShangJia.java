package com.kaigekeji.zhinengshibie.dao.entity;

import java.io.Serializable;

public class OsShangJia implements Serializable {
    private String bianHao;

    private String mingCheng;

    private String jianCheng;

    private String jingYingLeiBie;

    private String tingCheChangBianHao;

    private String diKouXiaoShi;

    private static final long serialVersionUID = 1L;

    public String getBianHao() {
        return bianHao;
    }

    public void setBianHao(String bianHao) {
        this.bianHao = bianHao;
    }

    public String getMingCheng() {
        return mingCheng;
    }

    public void setMingCheng(String mingCheng) {
        this.mingCheng = mingCheng;
    }

    public String getJianCheng() {
        return jianCheng;
    }

    public void setJianCheng(String jianCheng) {
        this.jianCheng = jianCheng;
    }

    public String getJingYingLeiBie() {
        return jingYingLeiBie;
    }

    public void setJingYingLeiBie(String jingYingLeiBie) {
        this.jingYingLeiBie = jingYingLeiBie;
    }

    public String getTingCheChangBianHao() {
        return tingCheChangBianHao;
    }

    public void setTingCheChangBianHao(String tingCheChangBianHao) {
        this.tingCheChangBianHao = tingCheChangBianHao;
    }

    public String getDiKouXiaoShi() {
        return diKouXiaoShi;
    }

    public void setDiKouXiaoShi(String diKouXiaoShi) {
        this.diKouXiaoShi = diKouXiaoShi;
    }

    @Override
    public String toString() {
        return "OsShangJia{" +
                "bianHao='" + bianHao + '\'' +
                ", mingCheng='" + mingCheng + '\'' +
                ", jianCheng='" + jianCheng + '\'' +
                ", jingYingLeiBie='" + jingYingLeiBie + '\'' +
                ", tingCheChangBianHao='" + tingCheChangBianHao + '\'' +
                ", diKouXiaoShi='" + diKouXiaoShi + '\'' +
                '}';
    }
}