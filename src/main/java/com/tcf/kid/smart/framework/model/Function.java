package com.tcf.kid.smart.framework.model;

/***
 * TODO TCF 映射器接口中定义的持久化方法
 * @author 71485
 *
 */
public class Function {

	//TODO TCF SQL类型
	private String sqlType;
	
	//TODO TCF SQL
	private String sql;
	
	//TODO TCF 方法名
	private String methodName;
	
	//TODO TCF 参数类型
	private String parameterType;
	
	//TODO TCF 返回值类型
	private String resultType;
	
	public String getSqlType() {
		return sqlType;
	}
	public void setSqlType(String sqlType) {
		this.sqlType = sqlType;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getParameterType() {
		return parameterType;
	}
	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}
	public String getResultType() {
		return resultType;
	}
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}
	
}
