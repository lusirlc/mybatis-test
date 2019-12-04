package pers.luchuan.mybatis.design.sqlsession.proxy;

import lombok.AllArgsConstructor;
import pers.luchuan.mybatis.design.config.Mapper;
import pers.luchuan.mybatis.design.utils.Executor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;

/**
 * Created By Lu Chuan On 2019/11/28
 */
@AllArgsConstructor
public class MapperProxy implements InvocationHandler {
	private Map<String,Mapper> mappers;
	private Connection connection;
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String methodName = method.getName();
		String className = method.getDeclaringClass().getName();
		String key = className + "." + methodName;
		Mapper mapper = mappers.get(key);
		if (mapper == null) {
			throw new IllegalArgumentException("传入参数有误");
		}
		return new Executor().selectList(mapper,connection);
	}
}
