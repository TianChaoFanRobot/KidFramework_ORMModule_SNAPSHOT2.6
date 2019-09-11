package com.tcf.kid.smart.framework.core;

import java.lang.reflect.Proxy;

import com.tcf.kid.smart.framework.config.Configuration;
import com.tcf.kid.smart.framework.proxy.MapperProxyManager;

/***
 * TODO TCF KidORM SQL会话
 * TODO TCF 定义SQL执行策略，调用SQL执行器中的SQL执行策略实现数据持久化操作
 * TODO TCF 根据映射器接口类型创建JDK动态代理实例并调用代理方法
 * @author 71485
 *
 */
public class SqlSession {

	//TODO TCF KidORM 核心配置信息
	private Configuration configuration=new Configuration();
	
	//TODO TCF KidORM SQL执行器
	private Executor executor=new JDBCExecutor();
	
	//TODO TCF 定义SQL执行策略
	//TODO TCF 根据主键id查询唯一结果
	public Object selectOne(String sql,String parameter)
	{
		return executor.selectOne(sql,parameter);
	}
	
	//TODO TCF 根据映射器接口类型创建映射器接口JDK动态代理实例并调用代理方法实现数据持久化操作
	public Object getMapper(Class<?> mapperClass)
	{
		return Proxy.newProxyInstance(mapperClass.getClassLoader(), 
				                      new Class[] {mapperClass}, 
				                      new MapperProxyManager(configuration,this));
	}
}
