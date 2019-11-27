package pers.luchuan.mybatis.annotation.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created By Lu Chuan On 2019/11/26
 */
@Data
public class TestTb2 {
	private Long id;
	private Integer col_tinyint;
	private Integer col_smallint;
	private Integer col_int;
	private String col_varchar;
	private String col_char;
	private String col_text;
	private Date col_datetime;
	private Date col_timeStamp;
}
