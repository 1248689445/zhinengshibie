package com.kaigekeji.zhinengshibie.view;

import com.kaigekeji.zhinengshibie.dao.entity.Business;
import com.kaigekeji.zhinengshibie.service.BusinessService;
import com.kaigekeji.zhinengshibie.util.exception.ParamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

//@RestController
//@CrossOrigin   // 解决跨域问题
@Controller
@RequestMapping("Business")
public class BusinessAction {

    @Autowired
    BusinessService businessService;

    /**
     * @param business {@link Business}   商家信息
     * @return HashMap   json格式数据
     */
    @ResponseBody
    @RequestMapping(value = "/insertBusiness", method = RequestMethod.POST)
    public HashMap insertBusiness(Business business) {
        HashMap hashMap = new HashMap();
        try {
            businessService.insertSelective(business);
            hashMap.put("type", "ok");
            hashMap.put("data", business);
            hashMap.put("return", "新增成功！");
        } catch (ParamException e) {
            hashMap.put("type", "error");
            hashMap.put("errormsg", "参数不完整");
            e.printStackTrace();
        }
        return hashMap;
    }
}
