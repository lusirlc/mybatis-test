package pers.luchuan.mybatis.design.sqlsession;

import pers.luchuan.mybatis.design.config.Configuration;
import pers.luchuan.mybatis.design.sqlsession.defaults.DefaultSqlSessionFactory;
import pers.luchuan.mybatis.design.utils.XMLConfigBuilder;

import java.io.InputStream;

/**
 * Created By Lu Chuan On 2019/11/28
 */
public class SqlSessionFactoryBuilder {
	
	public static SqlSessionFactory build(InputStream cfg) {
		Configuration configuration = XMLConfigBuilder.loadConfiguration(cfg);
		return new DefaultSqlSessionFactory(configuration);
	}
}
