package pers.luchuan.mybatis.design.utils;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import pers.luchuan.mybatis.design.annotation.Select;
import pers.luchuan.mybatis.design.config.Configuration;
import pers.luchuan.mybatis.design.config.Mapper;
import pers.luchuan.mybatis.design.io.Resources;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created By Lu Chuan On 2019/11/27
 */
public class XMLConfigBuilder {
	public static Configuration loadConfiguration(InputStream config) {
		try {
			Configuration cfg = new Configuration();
			
			SAXReader reader = new SAXReader();
			Document document = reader.read(config);
			
			Element root = document.getRootElement();
			
			List<Element> propertyElements = root.selectNodes("//property");
			for (Element propertyElement : propertyElements) {
				String name = propertyElement.attributeValue("name");
				if (Objects.equals(name, "driver")) {
					String driver = propertyElement.attributeValue("value");
					cfg.setDriver(driver);
				}
				if (Objects.equals(name, "url")) {
					String url = propertyElement.attributeValue("value");
					cfg.setUrl(url);
				}
				if (Objects.equals(name, "username")) {
					String username = propertyElement.attributeValue("value");
					cfg.setUsername(username);
				}
				if (Objects.equals(name, "password")) {
					String password = propertyElement.attributeValue("value");
					cfg.setPassword(password);
				}
			}
			
			List<Element> mapperElements = root.selectNodes("//mappers/mapper");
			for (Element mapperElement : mapperElements) {
				Attribute attribute = mapperElement.attribute("resource");
				if (attribute != null) {
					System.out.println("使用的是xml配置");
					String mapperPath = attribute.getValue();
					Map<String, Mapper> mapper = loadMapperConfiguration(mapperPath);
					cfg.setMappers(mapper);
				} else {
					System.out.println("使用的是注解配置");
					String classPath = mapperElement.attributeValue("class");
					Map<String, Mapper> mapper = loadMapperAnnotation(classPath);
					cfg.setMappers(mapper);
				}
			}
			return cfg;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				config.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static Map<String, Mapper> loadMapperAnnotation(String classPath) {
		HashMap<String, Mapper> mappers = new HashMap<>();
		try {
			Class clazz = Class.forName(classPath);
			Method[] methods = clazz.getMethods();
			for (Method method : methods) {
				boolean b = method.isAnnotationPresent(Select.class);
				if (b) {
					Mapper mapper = new Mapper();
					Select selectAnnotation = method.getAnnotation(Select.class);
					String sql = selectAnnotation.value();
					Type returnType = method.getGenericReturnType();
					if (returnType instanceof ParameterizedType) {
						ParameterizedType type = (ParameterizedType) returnType;
						Type[] arguments = type.getActualTypeArguments();
						Class argClass = (Class) arguments[0];
						String resultType = argClass.getName();
						mapper.setSql(sql);
						mapper.setResultType(resultType);
					}
					String methodName = method.getName();
					String className = method.getDeclaringClass().getName();
					String key = className + "." + methodName;
					mappers.put(key, mapper);
				}
			}
			return mappers;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	private static Map<String, Mapper> loadMapperConfiguration(String mapperPath) {
		InputStream is = null;
		try {
			Map<String, Mapper> mappers = new HashMap<>();
			
			is = Resources.getResourceAsStream(mapperPath);
			SAXReader reader = new SAXReader();
			Document document = reader.read(is);
			Element root = document.getRootElement();
			
			String namespace = root.attributeValue("namespace");
			List<Element> selectNodes = root.selectNodes("//select");
			for (Element selectNode : selectNodes) {
				String id = selectNode.attributeValue("id");
				String resultType = selectNode.attributeValue("resultType");
				String text = selectNode.getText();
				String key = namespace + "." + id;
				
				Mapper mapper = new Mapper();
				mapper.setSql(text);
				mapper.setResultType(resultType);
				mappers.put(key, mapper);
			}
			return mappers;
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
