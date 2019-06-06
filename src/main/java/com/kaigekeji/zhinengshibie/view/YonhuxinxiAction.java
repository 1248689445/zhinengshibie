package com.kaigekeji.zhinengshibie.view;

import com.kaigekeji.zhinengshibie.dao.entity.YongHuXinXi;
import com.kaigekeji.zhinengshibie.service.YongHuXinXiService;
import com.kaigekeji.zhinengshibie.util.exception.InitialException;
import com.kaigekeji.zhinengshibie.util.exception.InsertException;
import com.kaigekeji.zhinengshibie.util.exception.ParamException;
import com.kaigekeji.zhinengshibie.util.share.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * 用户信息对外接口
 */
@Controller
@RequestMapping("YonhuxinxiAction")
public class YonhuxinxiAction {

    @Autowired
    private YongHuXinXiService yonghuXinxiService;   //用户业务层

    /**
     * 查询用户openid
     * @param openId {@link String} 用户openid
     * @return {@link HashMap} json格式数据
     */
    @ResponseBody
    @RequestMapping(value = "/findOpenid", method = RequestMethod.POST)
    public HashMap selectByOpenid(String openId) {
        HashMap hashMap = new HashMap();
        try {
            YongHuXinXi yonghuXinxi = yonghuXinxiService.selectByOpenId(openId);
            if(EmptyUtil.isBlank(yonghuXinxi)){
                hashMap.put("type", "ok");
                hashMap.put("data", "null");
                hashMap.put("return", "没有该Openid信息!");
            }else{
                hashMap.put("type", "ok");
                hashMap.put("data", yonghuXinxi);
                hashMap.put("return", "查询成功!");
            }
        } catch (ParamException e) {
            hashMap.put("type", "error");
            hashMap.put("errormsg", "参数不完整!");
            e.printStackTrace();
        } catch (InitialException e) {
            hashMap.put("type", "error");
            hashMap.put("errormsg", "查询失败!");
            e.printStackTrace();
        }
        return hashMap;
    }

    /**
     * 新增用户信息
     * @param openid {@link String}  用户openid
     * @param nicheng {@link String} 用户昵称
     * @param touxiang {@link String} 用户头像
     * @return {@link HashMap} json格式数据
     */
    @ResponseBody
    @RequestMapping(value = "Newusers", method = RequestMethod.POST)
    public HashMap insertSelective(String openid,String nicheng,String touxiang) {
        HashMap hashMap = new HashMap();
        try {
            YongHuXinXi yonghuXinxi= yonghuXinxiService.insertSelective(openid,nicheng,touxiang);
            hashMap.put("type", "ok");
            hashMap.put("data", yonghuXinxi);
            hashMap.put("errormsg", "新增成功!");
        } catch (InsertException e) {
            hashMap.put("type", "error");
            hashMap.put("errormsg", "新增失败!");
            e.printStackTrace();
        } catch (ParamException e) {
            hashMap.put("type", "error");
            hashMap.put("errormsg", "参数不完整!");
            e.printStackTrace();
        }
       return hashMap;
    }
}
