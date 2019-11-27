package pers.luchuan.mybatis.annotation.dao;

import org.apache.ibatis.annotations.Select;
import pers.luchuan.mybatis.annotation.entity.TestTb2;

import java.util.List;

/**
 * Created By Lu Chuan On 2019/11/26
 */
public interface TestTbDao2 {
	@Select("select * from test_tb")
	List<TestTb2> selectAll();
}
