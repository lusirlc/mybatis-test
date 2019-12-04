package pers.luchuan.mybatis.design.annotation;

import java.lang.annotation.*;

/**
 * Created By Lu Chuan On 2019/11/28
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Select {
	String value();
}
