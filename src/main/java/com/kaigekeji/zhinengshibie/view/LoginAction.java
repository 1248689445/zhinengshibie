package com.kaigekeji.zhinengshibie.view;

import com.kaigekeji.zhinengshibie.dao.entity.YongHuXinXi;
import com.kaigekeji.zhinengshibie.service.LoginService;
import com.kaigekeji.zhinengshibie.util.exception.InitialException;
import com.kaigekeji.zhinengshibie.util.exception.ParamException;
import com.kaigekeji.zhinengshibie.util.share.HttpRequest;
import com.kaigekeji.zhinengshibie.util.share.HttpRequestor;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.MediaType;
import java.util.HashMap;
import java.util.Map;

/**
 * 授权登录
 */
@Controller
@RequestMapping("LoginAction/")
public class LoginAction {
    public static final Logger logger= LoggerFactory.getLogger(LoginAction.class);

    @Autowired
    private LoginService loginService;


    /**
     * 微信授权登录
     * @param code              用户登录码
     * @param encryptedData     用户敏感信息加密数据
     * @param iv                加密算法的初始向量
     * @return {@link HashMap} json格式数据
     */
    @ResponseBody
    @RequestMapping(value = "Initialization", method = RequestMethod.POST)
    public HashMap Initialization(String code,String encryptedData,String iv){
        HashMap hashMap=new HashMap();
        logger.info("哎哎哎！！");
        String encryptedDatas=encryptedData.replaceAll(" +","+");
        try {
            YongHuXinXi yonghuXinxi= loginService.Initialization(code,encryptedDatas.replaceAll(" ",""),iv);
            hashMap.put("type", "ok");
            hashMap.put("data", yonghuXinxi);
            hashMap.put("return", "初始化成功!");
        } catch (InitialException e) {
            hashMap.put("type", "error");
            hashMap.put("errormsg", "初始化失败!");
            e.printStackTrace();
        } catch (ParamException e) {
            hashMap.put("type", "error");
            hashMap.put("errormsg", "参数不完整!");
            e.printStackTrace();
        }
        return hashMap;
    }
}
