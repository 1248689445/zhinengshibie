package com.kaigekeji.zhinengshibie.dao;

import com.kaigekeji.zhinengshibie.dao.entity.Business;

public interface BusinessMapper {

    int insertSelective(Business record);

    Business selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(Business record);


}