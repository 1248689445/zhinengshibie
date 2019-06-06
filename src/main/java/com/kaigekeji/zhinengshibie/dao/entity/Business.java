package com.kaigekeji.zhinengshibie.dao.entity;

import java.io.Serializable;

public class Business implements Serializable {
    private Integer sId;

    private String sName;

    private String jc;

    private String type;

    private String tId;

    private String diKouXiaoShi;

    private Integer ticket;

    private Integer count;

    private Integer countType;

    private String addDate;

    private String endDate;

    private Integer state;

    private static final long serialVersionUID = 1L;

    public Integer getsId() {
        return sId;
    }

    public void setsId(Integer sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getJc() {
        return jc;
    }

    public void setJc(String jc) {
        this.jc = jc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    public String getDiKouXiaoShi() {
        return diKouXiaoShi;
    }

    public void setDiKouXiaoShi(String diKouXiaoShi) {
        this.diKouXiaoShi = diKouXiaoShi;
    }

    public Integer getTicket() {
        return ticket;
    }

    public void setTicket(Integer ticket) {
        this.ticket = ticket;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCountType() {
        return countType;
    }

    public void setCountType(Integer countType) {
        this.countType = countType;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Business{" +
                "sId=" + sId +
                ", sName='" + sName + '\'' +
                ", jc='" + jc + '\'' +
                ", type='" + type + '\'' +
                ", tId='" + tId + '\'' +
                ", diKouXiaoShi='" + diKouXiaoShi + '\'' +
                ", ticket=" + ticket +
                ", count=" + count +
                ", countType=" + countType +
                ", addDate='" + addDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", state=" + state +
                '}';
    }
}