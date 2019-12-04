package pers.luchuan.mybatis.design.config;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created By Lu Chuan On 2019/11/27
 */
@Data
public class Configuration {
	private String url;
	private String username;
	private String password;
	private String driver;
	private Map<String,Mapper> mappers = new HashMap<>();
	
	public void setMappers(Map<String, Mapper> mappers) {
		this.mappers.putAll(mappers);
	}
}
