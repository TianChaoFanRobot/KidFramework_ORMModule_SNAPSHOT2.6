package com.tcf.kid.smart.framework.model;

import java.util.List;

/***
 * TODO TCF 映射器接口模型类：封装映射器接口信息
 * @author 71485
 *
 */
public class MapperBean {

	//TODO TCF 映射器接口名
	private String interfaceName;
	
	//TODO TCF 映射器接口中定义的所有持久化方法
	private List<Function> functions;
	
	public String getInterfaceName() {
		return interfaceName;
	}
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	public List<Function> getFunctions() {
		return functions;
	}
	public void setFunctions(List<Function> functions) {
		this.functions = functions;
	}
	
}
