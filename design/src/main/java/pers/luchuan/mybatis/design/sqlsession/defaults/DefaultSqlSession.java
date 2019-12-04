package pers.luchuan.mybatis.design.sqlsession.defaults;

import pers.luchuan.mybatis.design.config.Configuration;
import pers.luchuan.mybatis.design.sqlsession.SqlSession;
import pers.luchuan.mybatis.design.sqlsession.proxy.MapperProxy;
import pers.luchuan.mybatis.design.utils.DataSourceUtil;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created By Lu Chuan On 2019/11/28
 */
public class DefaultSqlSession implements SqlSession {
	private Configuration configuration;
	private Connection connection;
	
	public DefaultSqlSession(Configuration configuration) {
		this.configuration = configuration;
		this.connection = DataSourceUtil.getConnection(configuration);
	}
	
	@Override
	public <T> T getMapper(Class<T> clazz) {
		return (T) Proxy.newProxyInstance(clazz.getClassLoader(),
				new Class[]{clazz},new MapperProxy(configuration.getMappers(),connection));
	}
	
	@Override
	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
