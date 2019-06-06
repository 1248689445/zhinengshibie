package com.kaigekeji.zhinengshibie.service;

import com.fasterxml.jackson.module.jaxb.ser.DomElementJsonSerializer;
import com.kaigekeji.zhinengshibie.dao.AppInfoMapper;
import com.kaigekeji.zhinengshibie.dao.entity.AppInfo;
import com.kaigekeji.zhinengshibie.util.exception.InsertException;
import com.kaigekeji.zhinengshibie.util.exception.ParamException;
import com.kaigekeji.zhinengshibie.util.exception.UpdateException;
import com.kaigekeji.zhinengshibie.util.share.EmptyUtil;
import com.kaigekeji.zhinengshibie.util.share.HttpRequestor;
import com.kaigekeji.zhinengshibie.util.share.HttpRequestorImpl;
import com.kaigekeji.zhinengshibie.util.share.WeChatRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * 应用程序信息业务逻辑处理
 */
@Service
public class AppInfoService {

    @Autowired
    private AppInfoMapper appInfoMapper;      //应用程序信息

    @Value("#{system['signKg']}")
    private String signKg;


    @Transactional(rollbackFor = UpdateException.class, noRollbackFor = ParamException.class)
    public void updateToken() throws UpdateException, ParamException {
        AppInfo appInfo = appInfoMapper.selectBySign(signKg);
        this.updateToken(appInfo);
    }


    /**
     * 更新Token
     *
     * @param appInfo {@link AppInfo} 应用程序
     * @return {@link HashMap} json格式数据
     * @throws UpdateException 更新失败！
     * @throws ParamException  参数错误！
     */
    @Transactional(rollbackFor = UpdateException.class, noRollbackFor = ParamException.class)
    public HashMap updateToken(AppInfo appInfo) throws UpdateException, ParamException {
        HashMap hashMap = new HashMap();
        Map tokenMap = WeChatRequestUtil.getAccessToken(appInfo.getAppid(), appInfo.getSecret());
        String token = tokenMap.get("access_token").toString();

        if (token != null && !"".equals(token)) {
            appInfo.setToken(token);
            int num = appInfoMapper.updateBySign(appInfo);
            if (num <= 0) {
                throw new UpdateException();
            }
        } else {
            throw new ParamException();
        }
        return hashMap;
    }


    //test
    public static void main(String[] args) {
        HttpRequestor httpRequestor = new HttpRequestorImpl();
//        Map tokenMap = WeChatRequestUtil.getAccessToken("wx18385lalalala", "bef47459d81a6eflalalalala");
//        Map tokenMap = WeChatRequestUtil.getAccessToken("wxbe6f5c432f572398", "3a79f1875f946bc5fb41dbf0b4bc5faf");
        Map tokenMap = WeChatRequestUtil.getAccessToken("wx6c5f41c630469758", "a76d1e5da4613eac01f68fe45ef852c5");

        String token = tokenMap.get("access_token").toString();
        System.out.println(token);
    }
}


///**
// * 应用程序信息业务逻辑处理
// */
//        @Service
//        public class AppInfoService {
//
//        @Autowired
//        private AppInfoMapper appInfoMapper;      //应用程序信息
//
//        @Value("#{system['signKg']}")
//        private String signKg;
//
//
//        @Transactional
//        public void updateToken(){
//        AppInfo appInfo = appInfoMapper.selectBySign(signKg);
//        this.updateToken(appInfo);
//        }
//
//
//        /**
//         * 更新Token
//         * @param appInfo {@link AppInfo} 应用程序
//         * @return {@link HashMap} json格式数据
//         * @throws UpdateException  更新失败！
//         * @throws ParamException   参数错误！
//         */
//        @Transactional
//        public void updateToken(AppInfo appInfo){
//
//        try {
//        Map tokenMap = WeChatRequestUtil.getAccessToken(appInfo.getAppid(), appInfo.getSecret());
//        String token = tokenMap.get("access_token").toString();
//        if (token != null && !"".equals(token)) {
//        appInfo.setToken(token);
//        int num = appInfoMapper.updateBySign(appInfo);
//        if (num <= 0) {
//        System.out.println("更新token成功");
//        } else {
//        System.out.println("更新token失败");
//        }
//        }
//        }catch (Exception e){
//        System.out.println("更新token失败");
//        }
//        }
//
//
//        //test
//        public static void main(String[] args) {
//        HttpRequestor httpRequestor = new HttpRequestorImpl();
//        Map tokenMap = WeChatRequestUtil.getAccessToken("wxbe6f5c432f572398", "3a79f1875f946bc5fb41dbf0b4bc5faf");
//        String token = tokenMap.get("access_token").toString();
//        System.out.println(token);
//        }
//        }

