package pers.luchuan.mybatis.start.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import pers.luchuan.mybatis.start.entity.TestTb;
import pers.luchuan.mybatis.start.entity.TestTb2;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created By Lu Chuan On 2019/11/26
 */
public class TestTbDaoTest {
	
	@Test
	public void selectAll() throws Exception {
		//1.加载mybatis主配置文件
		InputStream is = Resources.getResourceAsStream("SqlMapperConfig.xml");
		//2.创建SqlSessionFactory工厂
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(is);
		//3.使用工厂创建SqlSession对象
		SqlSession sqlSession = factory.openSession();
		//4.使用sqlSession创建代理对象
//		TestTbDao mapper = sqlSession.getMapper(TestTbDao.class);
		TestTbDao2 mapper = sqlSession.getMapper(TestTbDao2.class);
//		List<TestTb> testTbs = mapper.selectAll();
		List<TestTb2> testTbs = mapper.selectAll();
		System.out.println(testTbs);
		//5.关闭资源
		sqlSession.close();
		is.close();
	}
}