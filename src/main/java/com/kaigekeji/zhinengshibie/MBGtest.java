package com.kaigekeji.zhinengshibie;

import java.io.File;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;  
import org.mybatis.generator.config.Configuration;  
import org.mybatis.generator.config.xml.ConfigurationParser;  
import org.mybatis.generator.internal.DefaultShellCallback; 
/**
 * 自动生成实体
 */
public class MBGtest {
	public static void main(String[] args) throws Exception {
        try {
        	MBGtest generatorSqlmap = new MBGtest();
            generatorSqlmap.generator();
            System.out.println("complete!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void generator() throws Exception{  
        List<String> warnings = new ArrayList<String>();  
        boolean overwrite = true;
        File configFile = new File("D:/project/zhinengshibie/src/main/resources/generatorConfig.xml");

        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);  
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);  
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);  
        myBatisGenerator.generate(null);  
    }  
}
