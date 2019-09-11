package com.tcf.kid.smart.framework.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.tcf.kid.smart.framework.config.Configuration;
import com.tcf.kid.smart.framework.core.SqlSession;
import com.tcf.kid.smart.framework.model.Function;
import com.tcf.kid.smart.framework.model.MapperBean;

/***
 * TODO TCF 映射器接口代理工厂类
 * TODO TCF 构造注入
 * TODO TCF 定义代理方法，调用SQL会话中的SQL执行策略实现数据持久化操作
 * @author 71485
 *
 */
public class MapperProxyManager implements InvocationHandler{

	//TODO TCF KidORM 核心配置信息
	private Configuration configuration;
	
	//TODO TCF KidORM SQL会话
	private SqlSession sqlSession;
	
	//TODO TCF 构造注入
	public MapperProxyManager(Configuration configuration,SqlSession sqlSession)
	{
		this.configuration=configuration;
		this.sqlSession=sqlSession;
	}
	
	//TODO TCF 代理方法，基于反射调用SQL会话中的SQL执行策略实现数据持久化操作
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
	{
		Object invokeResult=null;
		
		try
		{
			//TODO TCF 根据映射文件名加载映射器接口模型
			MapperBean mapperBean=configuration.loadMapperFile("UserInfoMapper.xml");
			
			if(mapperBean!=null)
			{
				//TODO TCF 映射器接口名
				String interfaceName=mapperBean.getInterfaceName();
				
				//TODO TCF 需要调用的目标方法所在映射器接口名必须和当前加载的映射器接口名相同
				if(StringUtils.isNotEmpty(interfaceName) && interfaceName.equals(method.getDeclaringClass().getName()))
				{
					//TODO TCF 映射器接口中定义的所有持久化方法
					List<Function> functions=mapperBean.getFunctions();
					
					if(functions!=null && functions.size()>0)
					{
						for(Function function:functions)
						{
							//TODO TCF 需要调用的目标方法名必须和当前加载的映射器接口中定义的方法名相同
							if(StringUtils.isNotEmpty(function.getMethodName()) && function.getMethodName().equals(method.getName()))
							{
								//TODO TCF 调用SQL会话中的SQL执行策略实现数据持久化操作
								invokeResult=sqlSession.selectOne(function.getSql(),args[0]!=null?args[0].toString():"");
							}
						}
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return invokeResult;
	}
}
