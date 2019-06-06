package com.kaigekeji.zhinengshibie.dao;

import java.util.List;

import com.kaigekeji.zhinengshibie.dao.entity.AppInfo;


/**
 * 应用程序信息数据接口
 */
public interface AppInfoMapper {

	/**
	 * 查询全部应用程序信息
	 * @return {@link List} 全部应用程序信息
	 */
	 List<AppInfo> selectByAll();
	
	/**
	 * 查询应用程序信息
	 * @param sign {@link String} 应用程序标识
	 * @return {@link AppInfo}
	 */
	 AppInfo selectBySign(String sign);
	
	/**
	 * 修改应用程序信息
	 * @return {@link Integer} 受影响行数
	 */
	 Integer updateBySign(AppInfo appInfo);
	
}
