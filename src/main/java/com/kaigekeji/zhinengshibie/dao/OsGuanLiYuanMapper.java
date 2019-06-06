package com.kaigekeji.zhinengshibie.dao;

import com.kaigekeji.zhinengshibie.dao.entity.OsGuanLiYuan;

public interface OsGuanLiYuanMapper {


    int insertSelective(OsGuanLiYuan record);

    OsGuanLiYuan selectByPrimaryKey(String bianhao);

    int updateByPrimaryKeySelective(OsGuanLiYuan record);


}