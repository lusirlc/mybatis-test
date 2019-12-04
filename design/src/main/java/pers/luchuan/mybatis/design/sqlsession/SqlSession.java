package pers.luchuan.mybatis.design.sqlsession;

/**
 * Created By Lu Chuan On 2019/11/28
 */
public interface SqlSession {
	<T> T getMapper(Class<T> clazz);
	
	void close();
}
