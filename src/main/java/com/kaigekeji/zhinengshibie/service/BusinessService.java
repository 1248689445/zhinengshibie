package com.kaigekeji.zhinengshibie.service;

import com.kaigekeji.zhinengshibie.dao.BusinessMapper;
import com.kaigekeji.zhinengshibie.dao.entity.Business;
import com.kaigekeji.zhinengshibie.util.exception.InsertException;
import com.kaigekeji.zhinengshibie.util.exception.ParamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class BusinessService {

    @Autowired
    private BusinessMapper businessMapper;       //接口


    @Transactional(rollbackFor = ParamException.class,     //回滚
            noRollbackFor = {ParamException.class}) //不回滚
    public int insertSelective(Business business) throws ParamException {
        //必填字段验证是否为空
        if (business.getsName() == null || business.getsName().trim().equalsIgnoreCase("")) {
            throw new ParamException();   //参数值为空
        }
        int result = 0;
        try {
            result = businessMapper.insertSelective(business);
            if (result < 0) {
                throw new ParamException();
            }
        } catch (ParamException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 添加商家信息
     * @param sname 商家名称
     * @return 商家封装对象
     * @throws ParamException  参数错误
     * @throws InsertException 新增失败
     */
    @Transactional(rollbackFor = InsertException.class, noRollbackFor = {ParamException.class})
    public Business tianJiaShangJiaXinXi(String sname) throws ParamException, InsertException {
        if (sname == null || sname.trim().equalsIgnoreCase("")) {
            throw new ParamException();
        }


        Business business = new Business();
        business.setsName(sname);
        if (businessMapper.insertSelective(business) <= 0) {

            throw new InsertException();

        }

        return businessMapper.selectByPrimaryKey(business.getsId());
    }


}