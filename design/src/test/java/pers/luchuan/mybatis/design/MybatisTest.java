package pers.luchuan.mybatis.design;

import pers.luchuan.mybatis.design.dao.TestTbDao;
import pers.luchuan.mybatis.design.entity.TestTb;
import pers.luchuan.mybatis.design.io.Resources;
import pers.luchuan.mybatis.design.sqlsession.SqlSession;
import pers.luchuan.mybatis.design.sqlsession.SqlSessionFactory;
import pers.luchuan.mybatis.design.sqlsession.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created By Lu Chuan On 2019/11/28
 */
public class MybatisTest {
	public static void main(String[] args) {
		//1.读取配置文件
		InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
		//2.创建SqlSessionFactory工厂
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(in);
		//3.使用工厂生产SqlSession对象
		SqlSession session = factory.openSession();
		//4.使用SqlSession创建Dao接口的代理对象
		TestTbDao testTbDao = session.getMapper(TestTbDao.class);
		//5.使用代理对象执行方法
		List<TestTb> list = testTbDao.selectAll();
		for (TestTb testTb : list) {
			System.out.println(testTb);
		}
		//6.释放资源
		session.close();
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
