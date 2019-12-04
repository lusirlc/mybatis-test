package pers.luchuan.mybatis.design.dao;

import pers.luchuan.mybatis.design.entity.TestTb;

import java.util.List;

/**
 * Created By Lu Chuan On 2019/11/28
 */
public interface TestTbDao {
	List<TestTb> selectAll();
}
