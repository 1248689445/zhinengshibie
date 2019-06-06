package com.kaigekeji.zhinengshibie.view;

import com.kaigekeji.zhinengshibie.dao.entity.AppInfo;
import com.kaigekeji.zhinengshibie.service.AppInfoService;
import com.kaigekeji.zhinengshibie.util.exception.ParamException;
import com.kaigekeji.zhinengshibie.util.exception.UpdateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * 应用程序对外接口
 */
@Controller
@RequestMapping("AppInfoAction/")
public class AppInfoAction {

    @Autowired
    private AppInfoService appInfoService;


    @Value("#{constant['sign']}")
    private String sign;


    /**
     * 更新Token
     * @param request {@link HttpServletRequest} 请求sign参数
     * @return {@link HashMap} json格式数据
     */
    @ResponseBody
    @RequestMapping(value = "updateToke",method = RequestMethod.POST)
    public HashMap updateToke(HttpServletRequest request){
        String newsign = request.getParameter("sign");     //小程序域名
        HashMap hashMap = new HashMap();
        try {
            if (sign.equals(newsign)) {
                System.out.println("传过来的："+newsign);
                System.out.println("配置文件定义的:"+sign);
                appInfoService.updateToken();
            }
        } catch (UpdateException e) {
            e.printStackTrace();
        }catch (ParamException e){
            e.printStackTrace();
        }
        return  hashMap;
    }

}
