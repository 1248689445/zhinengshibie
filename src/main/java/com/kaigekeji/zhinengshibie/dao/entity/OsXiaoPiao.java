package com.kaigekeji.zhinengshibie.dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OsXiaoPiao implements Serializable {
    private String bianHao;

    private String chePai;

    private String yongHuBianHao;

    private String shangJiaBianHao;

    private Date saoMiaoShiJian;

    private Date kaiPiaoShiJian;

    private String xiaoPiaoBianHao;

    private BigDecimal shiFuJine;

    private String zhaoPianDiZhi;

    private String jieXiWenBen;

    private static final long serialVersionUID = 1L;

    public String getBianHao() {
        return bianHao;
    }

    public void setBianHao(String bianHao) {
        this.bianHao = bianHao;
    }

    public String getChePai() {
        return chePai;
    }

    public void setChePai(String chePai) {
        this.chePai = chePai;
    }

    public String getYongHuBianHao() {
        return yongHuBianHao;
    }

    public void setYongHuBianHao(String yongHuBianHao) {
        this.yongHuBianHao = yongHuBianHao;
    }

    public String getShangJiaBianHao() {
        return shangJiaBianHao;
    }

    public void setShangJiaBianHao(String shangJiaBianHao) {
        this.shangJiaBianHao = shangJiaBianHao;
    }

    public Date getSaoMiaoShiJian() {
        return saoMiaoShiJian;
    }

    public void setSaoMiaoShiJian(Date saoMiaoShiJian) {
        this.saoMiaoShiJian = saoMiaoShiJian;
    }

    public Date getKaiPiaoShiJian() {
        return kaiPiaoShiJian;
    }

    public void setKaiPiaoShiJian(Date kaiPiaoShiJian) {
        this.kaiPiaoShiJian = kaiPiaoShiJian;
    }

    public String getXiaoPiaoBianHao() {
        return xiaoPiaoBianHao;
    }

    public void setXiaoPiaoBianHao(String xiaoPiaoBianHao) {
        this.xiaoPiaoBianHao = xiaoPiaoBianHao;
    }

    public BigDecimal getShiFuJine() {
        return shiFuJine;
    }

    public void setShiFuJine(BigDecimal shiFuJine) {
        this.shiFuJine = shiFuJine;
    }

    public String getZhaoPianDiZhi() {
        return zhaoPianDiZhi;
    }

    public void setZhaoPianDiZhi(String zhaoPianDiZhi) {
        this.zhaoPianDiZhi = zhaoPianDiZhi;
    }

    public String getJieXiWenBen() {
        return jieXiWenBen;
    }

    public void setJieXiWenBen(String jieXiWenBen) {
        this.jieXiWenBen = jieXiWenBen;
    }

    @Override
    public String toString() {
        return "OsXiaoPiao{" +
                "bianHao='" + bianHao + '\'' +
                ", chePai='" + chePai + '\'' +
                ", yongHuBianHao='" + yongHuBianHao + '\'' +
                ", shangJiaBianHao='" + shangJiaBianHao + '\'' +
                ", saoMiaoShiJian=" + saoMiaoShiJian +
                ", kaiPiaoShiJian=" + kaiPiaoShiJian +
                ", xiaoPiaoBianHao='" + xiaoPiaoBianHao + '\'' +
                ", shiFuJine=" + shiFuJine +
                ", zhaoPianDiZhi='" + zhaoPianDiZhi + '\'' +
                ", jieXiWenBen='" + jieXiWenBen + '\'' +
                '}';
    }
}