package com.kaigekeji.zhinengshibie.util.piao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.HashMap;
import java.util.Map;

/****
 * 维也纳小票文本解析
 */
public class JieXiXiaoPiao {

    /**
     *  维也纳小票文本解析
     * @param jsonString  百度识别返回的json字符串
     * @return
     */
    public static Map<String,String> jiexiWYN(String jsonString){
        Map<String,String> resuleMap = new HashMap<String, String>();

        JSONArray result = JSON.parseObject(jsonString).getJSONArray("words_result");
        int size = result.size();
        for (int i = 0; i < size; i++) {
            String str = result.getJSONObject(i).getString("words");
            if (str.startsWith("账单号")) {
                str = str.substring(4,12);
                resuleMap.put("账单号",str);
                System.out.println("账单号   " + str);
            }
            if (str.startsWith("离店时间")) {
                str = str.substring(5,20).replaceAll("(.{10})", "$1 ");

                resuleMap.put("离店时间",str);
                System.out.println("离店时间   " + str);
            }
            if (str.startsWith("余额")) {
                str = result.getJSONObject(i - 1).getString("words");
                resuleMap.put("实付金额",str);
                System.out.println("金额   " + str);
            }
        }
        return resuleMap;
    }


/*
    //YYYY-MM-DD HH:mm:ss      阳光瑞城（步步高）票
    public static String TIME_FORMAT1 = "\\d{4}[-]([0][1-9]|(1[0-2]))[-]([1-9]|([012]\\d)|(3[01]))(([0-1]{1}[0-9]{1})|([2]{1}[0-4]{1}))([:])(([0-5]{1}[0-9]{1}|[6]{1}[0]{1}))([:])((([0-5]{1}[0-9]{1}|[6]{1}[0]{1})))";
    //YYYYMMDD HH:mm      郴州阳光瑞城（步步高）票
    public static String TIME_FORMAT2 = "\\d{4}([0][1-9]|(1[0-2]))([1-9]|([012]\\d)|(3[01]))(([0-1]{1}[0-9]{1})|([2]{1}[0-4]{1}))([:])(([0-5]{1}[0-9]{1}|[6]{1}[0]{1}))";
    //YYYY/MM/DD HH:mm:ss      维也纳票
    public static String TIME_FORMAT3 = "\\d{4}[/]([0][1-9]|(1[0-2]))[/]([1-9]|([012]\\d)|(3[01]))(([0-1]{1}[0-9]{1})|([2]{1}[0-4]{1}))([:])(([0-5]{1}[0-9]{1}|[6]{1}[0]{1}))([:])((([0-5]{1}[0-9]{1}|[6]{1}[0]{1})))";
    //HH:mmYYYY-MM-DD      庄影电影票
    public static String TIME_FORMAT4 = "([0-1]?[0-9]|2[0-3]):([0-5][0-9])\\d{4}-\\d{2}(-\\d{2})?";
//    ^((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29))\\s+([0-1]?[0-9]|2[0-3])-([0-5][0-9])-([0-5][0-9])

    //YYYY-MM-DD HH:mm     步步高票
    //public static String TIME_FORMAT5 = "\\d{4}[-]([0][1-9]|(1[0-2]))[-]([1-9]|([012]\\d)|(3[01]))(([0-1]{1}[0-9]{1})|([2]{1}[0-4]{1}))([:])(([0-5]{1}[0-9]{1}|[6]{1}[0]{1}))";

    //MMDD HH:mm     电影票
    //public static String TIME_FORMAT6 = "([0][1-9]|(1[0-2]))([1-9]|([012]\\d)|(3[01]))(([0-1]{1}[0-9]{1})|([2]{1}[0-4]{1}))([:])(([0-5]{1}[0-9]{1}|[6]{1}[0]{1}))";


    public static StringBuffer jieQuShiJian(String name, String shuju) {
        String result = "";
        List<String> results = new ArrayList<String>();
        if (name.contains("维也纳")) {
            Pattern pattern = Pattern.compile(TIME_FORMAT3);
            Matcher matcher = pattern.matcher(shuju);
            while (matcher.find()) {
                results.add(matcher.group(0));
            }
            result =  results.get(2);
        } else if (name.contains("郴州阳光瑞城")) {
            Pattern pattern = Pattern.compile(TIME_FORMAT2);
            Matcher matcher = pattern.matcher(shuju);
            while (matcher.find()) {
                result = matcher.group(0);
            }
        } else if (name.contains("阳光瑞城")) {
            Pattern pattern = Pattern.compile(TIME_FORMAT1);
            Matcher matcher = pattern.matcher(shuju);
            while (matcher.find()) {
                result = matcher.group(0);
            }
        } else if (name.contains("庄影")) {
            Pattern pattern = Pattern.compile(TIME_FORMAT4);
            Matcher matcher = pattern.matcher(shuju);
            while (matcher.find()) {
                result = matcher.group(0);
                result = result.substring(5) + result.substring(0, 5) ;
            }
        }
        //将时间转钟的符号全部去除返回
        result = result.replace("-", "")
                .replace("/", "")
                .replace(":", "");
                //.replaceAll("(.{8})", "$1 ")
        result = result.substring(0, 8) + " " + result.substring(8);
        return new StringBuffer(result);
    }
*/


}
