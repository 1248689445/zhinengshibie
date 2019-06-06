package com.kaigekeji.zhinengshibie.util.piao;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class yangguan {

    static  public void yang(String txt) {
        String money = "总金额:(.*?)元";


        String ph = "2012:12:51(.*?)自助收银台";

        Matcher omoney = Pattern.compile(money).matcher(txt);

        Matcher oph = Pattern.compile(ph).matcher(txt);
        while (oph.find()) {
            System.out.println("小票编号：" + oph.group(1));

            String sj = "阳光瑞城店(.*?)" + oph.group(1) + "";
            Matcher matcher2 = Pattern.compile(sj).matcher(txt);
            while (matcher2.find()) {
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String date = null;
                try {
                    date = sdf2.format(sdf1.parse(matcher2.group(1).replaceAll("\\D","")));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                System.out.println("时间：" + date);
                while (omoney.find()) {
                    System.out.println("金额：" + omoney.group(1));
                }
            }
        }

    }
}
