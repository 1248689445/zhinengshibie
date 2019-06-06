package com.kaigekeji.zhinengshibie.dao;

import com.kaigekeji.zhinengshibie.dao.entity.OsXiaoPiao;

public interface OsXiaoPiaoMapper {


    int insertSelective(OsXiaoPiao record);

    OsXiaoPiao selectByPrimaryKey(String bianhao);

    int updateByPrimaryKeySelective(OsXiaoPiao record);

}