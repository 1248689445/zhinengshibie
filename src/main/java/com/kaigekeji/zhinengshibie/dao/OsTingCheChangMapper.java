package com.kaigekeji.zhinengshibie.dao;

import com.kaigekeji.zhinengshibie.dao.entity.OsTingCheChang;

public interface OsTingCheChangMapper {


    int insertSelective(OsTingCheChang record);

    OsTingCheChang selectByPrimaryKey(String bianhao);

    int updateByPrimaryKeySelective(OsTingCheChang record);


}