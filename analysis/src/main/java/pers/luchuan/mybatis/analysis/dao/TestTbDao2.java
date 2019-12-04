package pers.luchuan.mybatis.analysis.dao;

import org.apache.ibatis.annotations.Select;
import pers.luchuan.mybatis.analysis.entity.TestTb2;

import java.util.List;

/**
 * Created By Lu Chuan On 2019/11/26
 */
public interface TestTbDao2 {
	List<TestTb2> selectAll();
}
