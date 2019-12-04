package pers.luchuan.mybatis.design.sqlsession.defaults;

import lombok.AllArgsConstructor;
import pers.luchuan.mybatis.design.config.Configuration;
import pers.luchuan.mybatis.design.sqlsession.SqlSession;
import pers.luchuan.mybatis.design.sqlsession.SqlSessionFactory;

/**
 * Created By Lu Chuan On 2019/11/28
 */
@AllArgsConstructor
public class DefaultSqlSessionFactory implements SqlSessionFactory {
	private Configuration configuration;
	@Override
	public SqlSession openSession() {
		return new DefaultSqlSession(configuration);
	}
}
