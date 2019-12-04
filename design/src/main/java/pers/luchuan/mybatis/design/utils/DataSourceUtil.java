package pers.luchuan.mybatis.design.utils;

import pers.luchuan.mybatis.design.config.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created By Lu Chuan On 2019/11/27
 */
public class DataSourceUtil {
	public static Connection getConnection(Configuration cfg) {
		try {
			Class.forName(cfg.getDriver());
			return DriverManager.getConnection(cfg.getUrl(),cfg.getUsername(),cfg.getPassword());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
