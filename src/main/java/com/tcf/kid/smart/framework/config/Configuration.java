package com.tcf.kid.smart.framework.config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.tcf.kid.smart.framework.model.Function;
import com.tcf.kid.smart.framework.model.MapperBean;

/***
 * TODO TCF KidORM 核心配置信息
 * @author 71485
 *
 */
public class Configuration {

	//TODO TCF 获取类加载器
	public ClassLoader getClassLoader()
	{
		return Thread.currentThread().getContextClassLoader();
	}
	
	//TODO TCF 根据数据源配置文件名加载数据源配置信息
	public Connection loadDataSource(String dataSourceFileName)
	{
		Connection connection=null;
		
		try
		{
			//TODO TCF 基于DOM4J解析数据源XML配置文件
			InputStream inputStream=getClassLoader().getResourceAsStream(dataSourceFileName);
			
			if(inputStream!=null)
			{
				SAXReader reader=new SAXReader();
				Document document=reader.read(inputStream);
				Element rootElement=document.getRootElement();
				
				if(rootElement!=null)
				{
					if(StringUtils.isNotEmpty(rootElement.getName()) && rootElement.getName().equals("dataSource"))
					{
						//TODO TCF 数据源配置信息
						//TODO TCF 数据库连接字符串
						String url="";
						
						//TODO TCF 用户名
						String userName="";
						
						//TODO TCF 密码
						String password="";
						
						for(Object node:rootElement.elements())
						{
							Element element=(Element)node;
							
							if(element!=null)
							{
								//TODO TCF 子节点name属性值
								String name=element.attributeValue("name");
								
								//TODO TCF 子节点配置信息
								String value=element.getText();
								
								if(StringUtils.isNotEmpty(name))
								{
									if(name.equals("url"))
									{
										url=value;
									}
									else if (name.equals("userName")) 
									{
										userName=value;
									}
									else if (name.equals("password")) 
									{
										password=value;
									}
								}
							}
						}
						
						if(StringUtils.isNotEmpty(url) && StringUtils.isNotEmpty(userName) && StringUtils.isNotEmpty(password))
						{
							connection=DriverManager.getConnection(url,userName,password);
						}
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return connection;
	}
	
	//TODO TCF 根据映射文件名加载映射器接口模型
	public MapperBean loadMapperFile(String mapperFileName)
	{
		MapperBean mapperBean=new MapperBean();
		
		try
		{
			//TODO TCF 基于DOM4J解析XML映射文件
			InputStream inputStream=getClassLoader().getResourceAsStream(mapperFileName);
			
			if(inputStream!=null)
			{
				SAXReader reader=new SAXReader();
				Document document=reader.read(inputStream);
				Element rootElement=document.getRootElement();
				
				if(rootElement!=null)
				{
					if(StringUtils.isNotEmpty(rootElement.getName()) && rootElement.getName().equals("mapper"))
					{
						//TODO TCF 命名空间(映射器接口名)
						String namespace=rootElement.attributeValue("namespace");
						mapperBean.setInterfaceName(namespace);
						
						//TODO TCF 封装映射器接口中定义的所有持久化方法
						List<Function> functions=new ArrayList<Function>();
						for(Object node:rootElement.elements())
						{
							Element element=(Element)node;
							
							if(element!=null)
							{
								//TODO TCF SQL类型
								String sqlType=element.getName();
								
								//TODO TCF SQL
								String sql=element.getText();
								
								//TODO TCF 方法名
								String methodName=element.attributeValue("id");
								
								//TODO TCF 参数类型
								String parameterType=element.attributeValue("parameterType");
								
								//TODO TCF 返回值类型
								String resultType=element.attributeValue("resultType");
								
								Function function=new Function();
								function.setSqlType(sqlType);
								function.setSql(sql);
								function.setMethodName(methodName);
								function.setParameterType(parameterType);
								function.setResultType(resultType);
								
								functions.add(function);
							}
						}
						
						//TODO TCF 映射器接口中定义的所有持久化方法
						mapperBean.setFunctions(functions);
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return mapperBean;
	}
}
