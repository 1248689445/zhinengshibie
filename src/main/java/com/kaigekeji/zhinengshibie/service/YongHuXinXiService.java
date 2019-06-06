package com.kaigekeji.zhinengshibie.service;

import com.kaigekeji.zhinengshibie.dao.YongHuXinXiMapper;
import com.kaigekeji.zhinengshibie.dao.entity.YongHuXinXi;
import com.kaigekeji.zhinengshibie.util.UUIDUtil;
import com.kaigekeji.zhinengshibie.util.exception.InitialException;
import com.kaigekeji.zhinengshibie.util.exception.InsertException;
import com.kaigekeji.zhinengshibie.util.exception.ParamException;
import com.kaigekeji.zhinengshibie.util.share.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 用户信息业务逻辑处理
 */
@Service
public class YongHuXinXiService {

    @Autowired
    private YongHuXinXiMapper yonghuXinxiMapper;   //用户dao接口

    /**
     * 查询用户openid
     *
     * @param openId {@link String}  String类型
     * @return {@link YongHuXinXi}  用户信息
     */
    @Transactional(rollbackFor = InitialException.class, noRollbackFor = ParamException.class)
    public YongHuXinXi selectByOpenId(String openId) throws InitialException, ParamException {
        if (openId == null || openId.trim().equalsIgnoreCase("")) {
            throw new ParamException();
        }
        return yonghuXinxiMapper.selectByOpenId(openId);
    }


    /**
     * 新增用户信息
     *
     * @param openid   {@link String}  用户openid
     * @param nicheng  {@link String} 用户昵称
     * @param touxiang {@link String} 用户头像
     * @return {@link YongHuXinXi} 用户封装对象
     * @throws InsertException 新增失败
     * @throws ParamException  参数错误
     */
    @Transactional(rollbackFor = InsertException.class, noRollbackFor = ParamException.class)
    public YongHuXinXi insertSelective(String openid, String nicheng, String touxiang)
            throws InsertException, ParamException {
        //验证必要的字段是否为空
        if (openid == null || openid.trim().equalsIgnoreCase("")
                || nicheng == null || nicheng.trim().equalsIgnoreCase("")
                || touxiang == null || touxiang.trim().equalsIgnoreCase("")) {
            throw new ParamException();
        }

        //开始新增
        YongHuXinXi yonghuXinxi = new YongHuXinXi();
        yonghuXinxi.setOpenId(openid);
        yonghuXinxi.setNiCheng(nicheng);
        yonghuXinxi.setTouXiang(touxiang);
        yonghuXinxi.setZhuCeShiJian(new Date());
        yonghuXinxi.setBianHao(UUIDUtil.getUUID());
        if (yonghuXinxiMapper.insertSelective(yonghuXinxi) <= 0) {
            throw new InsertException();
        }

        //返回ID
        return yonghuXinxiMapper.selectByOpenId(yonghuXinxi.getOpenId());
    }
}
