package com.tcf.kid.smart.framework.entity;

/***
 * TODO TCF 用户信息持久类
 * @author 71485
 *
 */
public class UserInfo {

	//TODO TCF 主键id
	private String id;
	
	//TODO TCF 用户名
	private String name;
	
	//TODO TCF 性别
	private String sex;
	
	//TODO TCF 年龄
	private Integer age;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
}
