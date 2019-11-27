package pers.luchuan.mybatis.start.dao;

import pers.luchuan.mybatis.start.entity.TestTb;

import java.util.List;

/**
 * Created By Lu Chuan On 2019/11/26
 */
public interface TestTbDao {
	List<TestTb> selectAll();
}
