package com.kaigekeji.zhinengshibie.dao;

import com.kaigekeji.zhinengshibie.dao.entity.OsShangJia;

public interface OsShangJiaMapper {


    int insertSelective(OsShangJia record);

    OsShangJia selectByPrimaryKey(String bianhao);

    int updateByPrimaryKeySelective(OsShangJia record);


}