package pers.luchuan.mybatis.design.utils;

import pers.luchuan.mybatis.design.config.Mapper;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * Created By Lu Chuan On 2019/11/28
 */
public class Executor {
	public <E> List<E> selectList(Mapper mapper, Connection connection) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String sql = mapper.getSql();
			String resultType = mapper.getResultType();
			Class clazz = Class.forName(resultType);
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
			//封装结果集
			List<E> list = new ArrayList<E>();//定义返回值
			if (resultSet.next()) {
				//实例化要封装的实体类对象
				E obj = (E)clazz.newInstance();
				
				//取出结果集的元信息：ResultSetMetaData
				ResultSetMetaData rsmd = resultSet.getMetaData();
				//取出总列数
				int columnCount = rsmd.getColumnCount();
				//遍历总列数
				for (int i = 1; i <= columnCount; i++) {
					//获取每列的名称，列名的序号是从1开始的
					String columnName = rsmd.getColumnName(i);
					//根据得到列名，获取每列的值
					Object columnValue = resultSet.getObject(columnName);
					//给obj赋值：使用Java内省机制（借助PropertyDescriptor实现属性的封装）
					PropertyDescriptor pd = new PropertyDescriptor(columnName,clazz);//要求：实体类的属性和数据库表的列名保持一种
					//获取它的写入方法
					Method writeMethod = pd.getWriteMethod();
					//把获取的列的值，给对象赋值
					writeMethod.invoke(obj,columnValue);
				}
				//把赋好值的对象加入到集合中
				list.add(obj);
			}
			return list;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			release(preparedStatement,resultSet);
		}
	}
	
	private void release(PreparedStatement preparedStatement,ResultSet resultSet){
		if(resultSet != null){
			try {
				resultSet.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		if(preparedStatement != null){
			try {
				preparedStatement.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
