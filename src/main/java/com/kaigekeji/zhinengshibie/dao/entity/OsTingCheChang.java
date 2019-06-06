package com.kaigekeji.zhinengshibie.dao.entity;

import java.io.Serializable;

public class OsTingCheChang implements Serializable {
    private String bianHao;

    private String mingCheng;

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

    @Override
    public String toString() {
        return "OsTingCheChang{" +
                "bianHao='" + bianHao + '\'' +
                ", mingCheng='" + mingCheng + '\'' +
                '}';
    }

}