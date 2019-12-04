package pers.luchuan.mybatis.design.io;

import java.io.InputStream;

/**
 * Created By Lu Chuan On 2019/11/27
 */
public class Resources {
	public static InputStream getResourceAsStream(String filePath) {
		return Resources.class.getClassLoader().getResourceAsStream(filePath);
	}
}
