package pers.luchuan.mybatis.analysis.service.impl;

import lombok.AllArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import pers.luchuan.mybatis.analysis.entity.TestTb;
import pers.luchuan.mybatis.analysis.service.TestTbService;

import java.util.List;

/**
 * Created By Lu Chuan On 2019/11/27
 */
@AllArgsConstructor
public class TestTbServiceImpl implements TestTbService {
	private SqlSessionFactory factory;
	public List<TestTb> selectAll() {
		SqlSession session = factory.openSession();
		List<TestTb> list = session.selectList("pers.luchuan.mybatis.analysis.dao.TestTbDao.selectAll");
		session.close();
		return list;
	}
}
