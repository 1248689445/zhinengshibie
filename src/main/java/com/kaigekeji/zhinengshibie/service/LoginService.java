package com.kaigekeji.zhinengshibie.service;

import com.kaigekeji.zhinengshibie.dao.AppInfoMapper;
import com.kaigekeji.zhinengshibie.dao.YongHuXinXiMapper;
import com.kaigekeji.zhinengshibie.dao.entity.AppInfo;
import com.kaigekeji.zhinengshibie.dao.entity.YongHuXinXi;
import com.kaigekeji.zhinengshibie.util.JSONBeanUtil;
import com.kaigekeji.zhinengshibie.util.UUIDUtil;
import com.kaigekeji.zhinengshibie.util.exception.InitialException;
import com.kaigekeji.zhinengshibie.util.exception.ParamException;
import com.kaigekeji.zhinengshibie.util.share.WeChatRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class LoginService {

    @Autowired
    private DataSourceTransactionManager txManager;

    @Autowired
    private AppInfoMapper appInfoMapper;

    @Autowired
    private YongHuXinXiMapper yonghuXinxiMapper;

    @Value("#{system['signKg']}")
    private String signKg;									//kg_xcx  域名


    /**
     * 授权登录
     * @param code              用户登录码
     * @param encryptedData     用户敏感信息加密数据
     * @param iv                加密算法的初始向量
     * @throws InitialException  授权失败
     * @throws ParamException    参数错误
     */
    @Transactional(rollbackFor = InitialException.class, noRollbackFor = ParamException.class)
    public YongHuXinXi Initialization(String code,String encryptedData,String iv) throws InitialException, ParamException{
        //验证必要的字段是否为空
        if (code==null || code.trim().equalsIgnoreCase("")
                && encryptedData==null || encryptedData.trim().equalsIgnoreCase("")
                && iv==null || iv.trim().equalsIgnoreCase("")){
            throw new ParamException();
        }

        Map map = new HashMap();
//
            AppInfo appInfo = appInfoMapper.selectBySign("kg_xcx");

            //获取微信小程序会话密钥
            Map sessionKey = WeChatRequestUtil.getSessionKey(appInfo.getAppid(), appInfo.getSecret(), code);
//        Map sessionKey = WeChatRequestUtil.getSessionKey("wxf6f2e6af0e9d95a0", "93fd4fcc9170478f3be1380b07dfcab4", code);


            // 获取解密用户信息,向腾讯拿到用户信息
//        System.out.println(sessionKey.get("session_key"));

            String session_key = String.valueOf(sessionKey.get("session_key"));
        String encryptedDataS = String.valueOf(encryptedData);
        System.out.println(encryptedDataS);
        String ivS = String.valueOf(iv);

            Map userInfoMap =WeChatRequestUtil.getUserInfo(session_key, encryptedDataS, ivS);

            String[] keys = {  "openId", "avatarUrl", "nickName" };
            // 保存普通用户登录信息
            map = JSONBeanUtil.getMap(userInfoMap, keys);

        YongHuXinXi user = yonghuXinxiMapper.selectByOpenId((String) map.get("openId"));
            if(user == null) {
                YongHuXinXi yonghuXinxi = new YongHuXinXi();
                yonghuXinxi.setBianHao(UUIDUtil.getUUID());
                yonghuXinxi.setOpenId((String) map.get("openId"));
                yonghuXinxi.setNiCheng((String) map.get("nickName"));
                yonghuXinxi.setTouXiang((String) map.get("avatarUrl"));
                yonghuXinxi.setZhuCeShiJian(new Date());
                int num = yonghuXinxiMapper.insertSelective(yonghuXinxi);
                if(num < 1) {
                    throw new InitialException();
                }
                return yonghuXinxiMapper.selectByOpenId(yonghuXinxi.getOpenId());
            }
            return user;
        }
}
