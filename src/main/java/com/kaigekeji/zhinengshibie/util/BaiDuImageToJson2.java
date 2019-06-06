

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

public class BaiDuImageToJson2 {

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


          //步步高
//        String jsonStr = "{\"log_id\": 9031141624830864670, \"words_result_num\": 26, \"words_result\": [{\"words\": \"步高超市郴州广场店\"}, {\"words\": \"2019052416:00\"}, {\"words\": \"205466收银台:009\"}, {\"words\": \"收银员:000107\"}, {\"words\": \"商品编码品名\"}, {\"words\": \"数量金额\"}, {\"words\": \"800000069黑布林\"}, {\"words\": \"0.6820.30\"}, {\"words\": \"800318997益达56g无糖西100\"}, {\"words\": \"9.90\"}, {\"words\": \"8000000子笑荔枝\"}, {\"words\": \"0.9118.00\"}, {\"words\": \"800155941嘉顿100g香葱薄1.00\"}, {\"words\": \"9.50\"}, {\"words\": \"总数量:3.59总金额\"}, {\"words\": \"57.7\"}, {\"words\": \"大写金额:伍拾元柒角整\"}, {\"words\": \"微信支付57.70(日期:20190524)\"}, {\"words\": \"实付57.70找零:\"}, {\"words\": \"0.00\"}, {\"words\": \"会员号:6650000007281650\"}, {\"words\": \"本次积分:5\"}, {\"words\": \"总计积分:6\"}, {\"words\": \"温馨提示:小票是您退换货的依据,凭\"}, {\"words\": \"小票1个月内开具发票,请妥善保管\"}, {\"words\": \"服务热线:2165808欢迎下次光临\"}]}\n";
          //阳光瑞城
//        String jsonStr = "{\"log_id\": 2032698503377562814, \"words_result_num\": 56, \"words_result\": [{\"words\": \"阳光瑞城店\"}, {\"words\": \"2019-05-2012:12:51\"}, {\"words\": \"66542自助收银台:01收银员:5702\"}, {\"words\": \"商品编码\"}, {\"words\": \"品名\"}, {\"words\": \"数量金额\"}, {\"words\": \"800044895\"}, {\"words\": \"0.37201.20\"}, {\"words\": \"800044945\"}, {\"words\": \"0.36202.50\"}, {\"words\": \"800044895\"}, {\"words\": \"800044945\"}, {\"words\": \"黄生黄生黄生黄生黄\"}, {\"words\": \"菜菜瓜瓜\"}, {\"words\": \"0.37201.20\"}, {\"words\": \"0.36202.50\"}, {\"words\": \"800044895\"}, {\"words\": \"0.37201.20\"}, {\"words\": \"800044945\"}, {\"words\": \"0.36202.50\"}, {\"words\": \"800044895\"}, {\"words\": \"0.37201.20\"}, {\"words\": \"800044945\"}, {\"words\": \"0.36202.50\"}, {\"words\": \"800044895\"}, {\"words\": \"800044945\"}, {\"words\": \"800044895\"}, {\"words\": \"800044945\"}, {\"words\": \"800044895\"}, {\"words\": \"黄生黄黄生\"}, {\"words\": \"菜瓜菜爪\"}, {\"words\": \"0.37201.20\"}, {\"words\": \"0.36202.50\"}, {\"words\": \"0.37201.20\"}, {\"words\": \"0.36202.50\"}, {\"words\": \"0.37201.20\"}, {\"words\": \"800044945\"}, {\"words\": \"0.36202.50\"}, {\"words\": \"折扣前\"}, {\"words\": \"2.53\"}, {\"words\": \"101581351之然散装级前腿\"}, {\"words\": \"0.07002.50\"}, {\"words\": \"折扣前\"}, {\"words\": \"2.51\"}, {\"words\": \"总数量:3\"}, {\"words\": \"折后总金额:6.20元\"}, {\"words\": \"支付优惠金额:(微信支付优惠)1.88元\"}, {\"words\": \"实际支付金额:4.32元\"}, {\"words\": \"大写金额:肆元叁角贰分\"}, {\"words\": \"支付方式:微信人脸支付\"}, {\"words\": \"会员账户:178****2642\"}, {\"words\": \"温馨提示:请在购小程序中自助开具电子发票,\"}, {\"words\": \"查看积分信息,欢迎息再次光临!\"}, {\"words\": \"回\"}, {\"words\": \"微信扫一扫,查看积分,领取优惠券,更多惊喜等你领取!\"}, {\"words\": \"步步高智慧零售\"}]}\n";
          //维也纳
//        String jsonStr = "{\"log_id\": 5115710069983592414, \"words_result_num\": 35, \"words_result\": [{\"words\": \"维也纳酒店集团\"}, {\"words\": \" Vienna Hotels Group\"}, {\"words\": \"维也纳国际酒店湖南郴州青年大道\"}, {\"words\": \"宾客账单\"}, {\"words\": \"房号姓名\"}, {\"words\": \"个人公司会员号\"}, {\"words\": \"135\"}, {\"words\": \"账单号19369054\"}, {\"words\": \"结账号\"}, {\"words\": \"来店时间:201905201425434\"}, {\"words\": \"离店时间:2019052116.00:00\"}, {\"words\": \"时间项目借方贷方房号\"}, {\"words\": \"05-20银行卡(手\"}, {\"words\": \"294.002107\"}, {\"words\": \"合余\"}, {\"words\": \"计额\"}, {\"words\": \":\"}, {\"words\": \"0.00\"}, {\"words\": \"29400\"}, {\"words\": \"-294.00\"}, {\"words\": \"以上房费已经包含饮用水\"}, {\"words\": \"宾客签字:\"}, {\"words\": \"预定电话:400-888-2888\"}, {\"words\": \"酒店电话:0735-5555888\"}, {\"words\": \"酒店传真:0735-5555611\"}, {\"words\": \"酒店地址:湖南郴州市苏仙区青年大道\"}, {\"words\": \"333号\"}, {\"words\": \"集团网址:\"}, {\"words\": \" www.wyn88. com\"}, {\"words\": \"打印时间:2019052016:0924\"}, {\"words\": \"打印人\"}, {\"words\": \"周秀\"}, {\"words\": \"退房人\"}, {\"words\": \"(\"}, {\"words\": \")\"}]}\n";  //yes
         //庄影影城
//        String jsonStr="{\"log_id\": 6015410409772003614, \"words_result_num\": 13, \"words_result\": [{\"words\": \"影影城\"}, {\"words\": \"测试打印内容\"}, {\"words\": \"15:502019-05-20\"}, {\"words\": \"测试打印内容\"}, {\"words\": \"测试厅测试厅测…99排99座\"}, {\"words\": \"测试打印内容\"}, {\"words\": \" IMAX3D\"}, {\"words\": \"映价999.99元\"}, {\"words\": \"服费999.99元\"}, {\"words\": \"时间05201550\"}, {\"words\": \"类试测满\"}, {\"words\": \"12456\"}, {\"words\": \"9012345\"}]}\n";
        //电子小票
//        String jsonStr="{\"log_id\": 2060998212953780734, \"words_result_num\": 40, \"words_result\": [{\"words\": \"4G7.8k/s15:38\"}, {\"words\": \"令75%\"}, {\"words\": \"电子小票\"}, {\"words\": \"●●●\"}, {\"words\": \"阳光瑞城店(120275)\"}, {\"words\": \"交易pos机编号:012\"}, {\"words\": \"回Q\"}, {\"words\": \"交易编码:29504\"}, {\"words\": \"小票二维码\"}, {\"words\": \"商品/编码\"}, {\"words\": \"数量\"}, {\"words\": \"单价\"}, {\"words\": \"金额\"}, {\"words\": \"雕牌206g×2加…(×34\"}, {\"words\": \"￥8.50\"}, {\"words\": \"￥289.00\"}, {\"words\": \"800336677\"}, {\"words\": \"折扣后￥130.56\"}, {\"words\": \"商品总金额\"}, {\"words\": \"￥289.00\"}, {\"words\": \"优惠抵扣\"}, {\"words\": \"￥158.40\"}, {\"words\": \"商品促销折扣(88.40)、大会员(70.00)\"}, {\"words\": \"实际支付金额\"}, {\"words\": \"￥130.60\"}, {\"words\": \"11温馨提示\"}, {\"words\": \"电子小票是您退换货的唯一凭证;请在30天内开具发票\"}, {\"words\": \"交易业态\"}, {\"words\": \"超市\"}, {\"words\": \"收银员\"}, {\"words\": \"002011\"}, {\"words\": \"交易渠道\"}, {\"words\": \" HYF\"}, {\"words\": \"支付渠道\"}, {\"words\": \"现金(130.10)、零钞消费(0.50)\"}, {\"words\": \"实付金额\"}, {\"words\": \"￥130.60\"}, {\"words\": \"查看发票\"}, {\"words\": \"我要开票\"}, {\"words\": \"三\"}]}\n";

//        String jsonStr = imageToJsonStr(new File("C:\\Users\\kaige\\Desktop\\步步高.jpg"));  //yes
        String jsonStr = imageToJsonStr(new File("C:\\Users\\kaige\\Desktop\\阳光瑞城.jpg")); //yes
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
            } else if (words.contains("郴州广场店")||words.contains("步步高")||words.contains("步高")) {
                System.out.println("步步高");
                System.out.println("时间：" + getbubuDate(jsonArray));
                System.out.println("金额：" + getMoney(jsonArray));
                System.out.println("id号：" + getBainhao(jsonArray));
                System.out.println("会员号：" + getHuiyuan(jsonArray));
                return words;
            } else if(words.contains("影城")||words.contains("庄影")){
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