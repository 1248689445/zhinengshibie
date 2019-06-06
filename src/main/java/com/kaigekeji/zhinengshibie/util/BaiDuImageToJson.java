

package com.kaigekeji.zhinengshibie.util;


import com.kaigekeji.zhinengshibie.util.exception.ImageToJSonStrException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaiDuImageToJson {

    public static String API_KEY = "9mWCUfF4Dni1rlegMbNlI5ku";
    public static String SECRET_KEY = "cvbINaKA6NIxkodYR46bHqDXcFjmUmNR";
    public static String BAIDU_API_URL = "https://aip.baidubce.com/rest/2.0/ocr/v1/accurate_basic";
    public static String ACCESS_TOKEN = "24.da1edc3ec748d10e879cda3a7254c18d.2592000.1561275689.282335-16335670";


    public static void initData(String API_KEY, String SECRET_KEY, String BAIDU_API_URL, String ACCESS_TOKEN) {
        BaiDuImageToJson2.API_KEY = API_KEY;
        BaiDuImageToJson2.SECRET_KEY = SECRET_KEY;
        BaiDuImageToJson2.BAIDU_API_URL = BAIDU_API_URL;
        BaiDuImageToJson2.ACCESS_TOKEN = ACCESS_TOKEN;
    }

    public static String imageToJsonStr(File file) throws ImageToJSonStrException {
        try {
            byte[] imgData = FileUtil.readFileByBytes(file.getPath());
            String imgStr = Base64Util.encode(imgData);
            String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imgStr, "UTF-8");
            String result = HttpUtil.post(BAIDU_API_URL, ACCESS_TOKEN, params);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ImageToJSonStrException();
        }
    }


    public static void main(String[] args) throws ImageToJSonStrException {

        //{"log_id": 3589017858016319740, "words_result_num": 56, "words_result": [{"words": "阳光瑞城店"}, {"words": "2019-05-2012:12:51"}, {"words": "66542自助收银台:01收银员:5702"}, {"words": "商品编码"}, {"words": "品名"}, {"words": "数量金额"}, {"words": "800044895"}, {"words": "0.37201.20"}, {"words": "800044945"}, {"words": "0.36202.50"}, {"words": "800044895"}, {"words": "800044945"}, {"words": "黄生黄生黄生黄生黄"}, {"words": "菜菜瓜瓜"}, {"words": "0.37201.20"}, {"words": "0.36202.50"}, {"words": "800044895"}, {"words": "0.37201.20"}, {"words": "800044945"}, {"words": "0.36202.50"}, {"words": "800044895"}, {"words": "0.37201.20"}, {"words": "800044945"}, {"words": "0.36202.50"}, {"words": "800044895"}, {"words": "800044945"}, {"words": "800044895"}, {"words": "800044945"}, {"words": "800044895"}, {"words": "黄生黄黄生"}, {"words": "菜瓜菜爪"}, {"words": "0.37201.20"}, {"words": "0.36202.50"}, {"words": "0.37201.20"}, {"words": "0.36202.50"}, {"words": "0.37201.20"}, {"words": "800044945"}, {"words": "0.36202.50"}, {"words": "折扣前"}, {"words": "2.53"}, {"words": "101581351之然散装级前腿"}, {"words": "0.07002.50"}, {"words": "折扣前"}, {"words": "2.51"}, {"words": "总数量:3"}, {"words": "折后总金额:6.20元"}, {"words": "支付优惠金额:(微信支付优惠)1.88元"}, {"words": "实际支付金额:4.32元"}, {"words": "大写金额:肆元叁角贰分"}, {"words": "支付方式:微信人脸支付"}, {"words": "会员账户:178****2642"}, {"words": "温馨提示:请在购小程序中自助开具电子发票,"}, {"words": "查看积分信息,欢迎息再次光临!"}, {"words": "回"}, {"words": "微信扫一扫,查看积分,领取优惠券,更多惊喜等你领取!"}, {"words": "步步高智慧零售"}]}

        String jsonStr = imageToJsonStr(new File("C:\\Users\\kaige\\Desktop\\步步高.jpg"));  //yes
//        String jsonStr = imageToJsonStr(new File("C:\\Users\\kaige\\Desktop\\阳光瑞城.jpg")); //yes
//        String jsonStr = imageToJsonStr(new File("C:\\Users\\kaige\\Desktop\\维也纳.jpg"));  //yes
//        String jsonStr = imageToJsonStr(new File("C:\\Users\\kaige\\Desktop\\8.jpg"));   //庄影影城  yes
//        String jsonStr = imageToJsonStr(new File("C:\\Users\\kaige\\Desktop\\阳光瑞城电子.jpg"));   //yes
        JSONObject jsonObject = new JSONObject(jsonStr);
        JSONArray jsonArray = jsonObject.getJSONArray("words_result");
        ygrc(jsonArray);

//        System.out.println("时间：" + getyangDate(jsonArray));
//        System.out.println("金额：" + getMoney(jsonArray));

//        System.out.println("id号：" + getBainhao(jsonArray));
//        System.out.println("会员号：" + getHuiyuan(jsonArray));
    }


    //根据标题
    public static String ygrc(JSONArray jsonArray)  {
        String words = "";
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
            words = jsonObject1.getString("words");

            if (words.contains("阳光瑞城") && words.indexOf("电子小票")<0) {
                System.out.println("阳光瑞城");
                System.out.println("时间：" + getyangDate(jsonArray));
                System.out.println("金额：" + getMoney(jsonArray));
                System.out.println("id号：" + getBainhao(jsonArray));
                System.out.println("会员号：" + getHuiyuan(jsonArray));
                return words;

            } else if (words.contains("维也纳酒店")) {
                System.out.println("维也纳");
                System.out.println("时间：" + getweiDate(jsonArray));
                System.out.println("金额：" + getMoney(jsonArray));
                System.out.println("id号：" + getBainhao(jsonArray));
                System.out.println("会员号被抹掉：" + getHuiyuan(jsonArray));
                return words;
            } else if (words.contains("郴州广场店")) {
                System.out.println("步步高");
                System.out.println("时间：" + getbubuDate(jsonArray));
                System.out.println("金额：" + getMoney(jsonArray));
                System.out.println("id号：" + getBainhao(jsonArray));
                System.out.println("会员号：" + getHuiyuan(jsonArray));
                return words;
            } else if(words.contains("影城")){
                System.out.println("庄影影城");
                System.out.println("时间：" + getyinchenDate(jsonArray));
                System.out.println("金额：" + getMoney(jsonArray));
                System.out.println("id号：" + getBainhao(jsonArray));
//                System.out.println("会员号：" + getHuiyuan(jsonArray));  //未发现
                return words;
            } else if(words.contains("电子小票")){
                System.out.println("电子小票");
//                System.out.println("时间：" + getyinchenDate(jsonArray));   //未发现
                System.out.println("金额：" + getDzMoney(jsonArray));
                System.out.println("id号：" + getBainhao(jsonArray));
                return words;
            }
        }
        return words;
    }


    //步步高时间
    public static String getbubuDate(JSONArray jsonArray) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");  //全部统一转存格式

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHH:mm");    //步步高  2019052416:00

        String path = "\\d{4}-\\d{6}:\\d{2}:\\d{2}";//步步高



        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
            String words = jsonObject1.getString("words");

            Matcher m =  Pattern.compile(path).matcher(words.trim());//阳光瑞城

            try {
                if (words.startsWith(DateUtil.getyyyy())||m.matches()) {    //根据年份来
                    if((words.contains(":")) || words.contains("-")|| words.contains("/")){
                        return sdf.format(sdf2.parse(words.trim()));
                    } else {
                        return null;
                    }
                }
            } catch (ParseException e) {

                e.getMessage();
            }
        }
        return null;
    }

    //阳光瑞城时间
    public static String getyangDate(JSONArray jsonArray) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");  //全部统一转存格式
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");  //阳光瑞城 2019-05-2012:12:51
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
            String words = jsonObject1.getString("words");
            try {
                int length=words.trim().replaceAll("\\D","").length();
                if (words.startsWith(DateUtil.getyyyy())&& length>10) {
                    return sdf.format(sdf1.parse(words.trim()));
                }

            } catch (ParseException e) {

                e.getMessage();
            }
        }
        return null;
    }

    //维也纳时间
    public static String getweiDate(JSONArray jsonArray) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");  //全部统一转存格式
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");  //维也纳 2019052116.00:00
        String path = "\\d{4}\\d{6}\\d{4}";//维也纳

        String REGEX_CHINESE = "[\u4e00-\u9fa5:.-/]";// 中文正则
        Pattern pat = Pattern.compile(REGEX_CHINESE);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
            String words = jsonObject1.getString("words");

            try {
                Matcher m =  Pattern.compile(path).matcher(words.trim());//阳光瑞城
                if (words.contains("离店")|| m.matches()) {
                    Matcher mat = pat.matcher(words.trim());
                    return sdf.format(sdf1.parse(mat.replaceAll("")));
                }else if(words.startsWith(DateUtil.getyyyy())){
                    Matcher mat = pat.matcher(words.trim());
                    return sdf.format(sdf1.parse(mat.replaceAll("")));
                }
            } catch (ParseException e) {
                e.getMessage();
            }
        }
        return null;
    }

    //庄影影城时间
    public static String getyinchenDate(JSONArray jsonArray) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");  //全部统一转存格式
        SimpleDateFormat sdf1 = new SimpleDateFormat("MMddHHmm");  //15:502019-05-20   05201550

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
            String words = jsonObject1.getString("words");
            try {
                if (words.contains("时间")) {
                    String sj = DateUtil.getyyyy() + "-" + sdf.format(sdf1.parse(words.trim().replaceAll("\\D", "")));
                    return sj;
                }
            } catch (ParseException e) {
                e.getMessage();
            }

        }
        return null;
    }

    //金额
    public static String getMoney(JSONArray jsonArray) {
        String REGEX_CHINESE = "[\u4e00-\u9fa5:]";// 中文正则
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
            String words = jsonObject1.getString("words");

            // 去除中文
            Pattern pat = Pattern.compile(REGEX_CHINESE);
            if (words.contains("实际支付金额:")||words.contains("实付")||words.contains("实付金额")
                    ||words.contains("票价")||words.contains("价")) {
                Matcher mat = pat.matcher(words.trim());
                return mat.replaceAll("");
            }else if(words.contains("计额")){       //维也纳
//                System.out.println("对应的下标是："+i);
                String jinqian=jsonArray.getJSONObject(i+3).get("words").toString();
                return jinqian;
            }
        }
        return null;
    }

    //电子小票金额
    public static String getDzMoney(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
            String words = jsonObject1.getString("words");
            if (words.contains("实付")||words.contains("实付金额")) {
                String jinqian=jsonArray.getJSONObject(i+1).get("words").toString();
                return jinqian.replaceAll("[￥]","");
            }
        }
        return null;
    }

    //id号
    public static String getBainhao(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
            String words = jsonObject1.getString("words");

            String ph = "(.*?)收银台";      //
            if(words.contains("账单号")||words.contains("编码") ){   // ||words.contains("房号")     //维纳也
                return words.replaceAll("\\D","");
            }else if(words.contains("收银台")||words.contains("银台")||words.contains("自助收")){      //步步高    阳光瑞城
                Matcher oph = Pattern.compile(ph).matcher(words);   //票号
                while (oph.find()) {
                    return oph.group().replaceAll("\\D","");
                }
            }else if(words.contains("影城")||words.contains("庄影")){    //庄影影城
                String piaohao1=jsonArray.getJSONObject(jsonArray.length()-1).toString().replaceAll("\\D","");
                String piaohao=jsonArray.getJSONObject(jsonArray.length()-2).toString().replaceAll("\\D","");
                return piaohao+piaohao1;
            }
        }
        return null;
    }

    //会员号
    public static String getHuiyuan(JSONArray jsonArray) {
        String REGEX_CHINESE = "[\u4e00-\u9fa5:]";// 中文正则
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
            String words = jsonObject1.getString("words");

            if (words.contains("会员")||words.contains("员号")) {
                // 去除中文
                Pattern pat = Pattern.compile(REGEX_CHINESE);
                Matcher mat = pat.matcher(words.trim());
                return mat.replaceAll("");
            }
        }
        return null;
    }
}