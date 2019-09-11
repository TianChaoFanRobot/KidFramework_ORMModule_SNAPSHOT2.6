package com.tcf.kid.smart.framework.core;

/***
 * TODO TCF SQL执行器接口，定义SQL执行策略
 * @author 71485
 *
 */
public interface Executor {

	//TODO TCF 根据主键id查询唯一结果
	public Object selectOne(String sql,String parameter);
	
}
