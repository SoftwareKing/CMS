package com.xujin.cms.auth;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 
 * @author Administrator
 *
 */
/**
 * @Description:只要在Controller上增加了这个方法的类，都需要进行权限的控制
 * @author xujin(作者)
 * @Version:V1.00(版本号)
 * @Create Date:2014-1-2(创建日期)
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthClass {
	/**
	 * 如果value为admin就表示这个类只能超级管理员访问
	 * 如果value为login表示这个类中的方法，某些可能为相应的角色可以访问
	 * @return
	 */
	public String value() default "admin";
}
