package pers.luchuan.mybatis.design.entity;

import lombok.Data;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created By Lu Chuan On 2019/11/26
 */
@Data
public class TestTb {
	private BigInteger id;
	private Integer col_tinyint;
	private Integer col_smallint;
	private Integer col_int;
	private String col_varchar;
	private String col_char;
	private String col_text;
	private Date col_datetime;
	private Date col_timeStamp;
}
