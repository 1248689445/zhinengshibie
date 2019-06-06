package com.kaigekeji.zhinengshibie.dao;

import com.kaigekeji.zhinengshibie.dao.entity.YongHuXinXi;

public interface YongHuXinXiMapper {

    /**
     * 查询用户openid
     * @param openid {@link String} String类型
     * @return {@link YongHuXinXi}   用户信息
     */
    YongHuXinXi selectByOpenId(String openid);


    /**
     * 新增用户信息
     * @param yonghuxinxi {@link YongHuXinXi}   用户信息
     * @return {@link int} 受影响行数 0：新增失败  1：新增成功
     */
    int insertSelective(YongHuXinXi yonghuxinxi);


}