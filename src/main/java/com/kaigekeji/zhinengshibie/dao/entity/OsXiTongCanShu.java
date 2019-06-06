package com.kaigekeji.zhinengshibie.dao.entity;

import java.io.Serializable;

public class OsXiTongCanShu implements Serializable {
    private String mingCheng;

    private String shuJu;

    private static final long serialVersionUID = 1L;

    public String getMingCheng() {
        return mingCheng;
    }

    public void setMingCheng(String mingCheng) {
        this.mingCheng = mingCheng;
    }

    public String getShuJu() {
        return shuJu;
    }

    public void setShuJu(String shuJu) {
        this.shuJu = shuJu;
    }

    @Override
    public String toString() {
        return "OsXiTongCanShu{" +
                "mingCheng='" + mingCheng + '\'' +
                ", shuJu='" + shuJu + '\'' +
                '}';
    }
}