package com.AE.system.util;

import com.AE.system.spring.SpringConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 提供全局spring容器
 * @author A.E.
 * @version 1.0
 */
public class SpringUtil {

    private SpringUtil() {}

    private static final ApplicationContext applicationContext;

    static {
        applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
    }

    /**
     * 无类型获取bean
     * @param name bean名
     * @return bean
     */
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    /**
     * 给定类型获取bean
     * @param name bean名
     * @param requiredType bean类型
     * @return bean
     * @param <T> 泛型
     */
    public static <T> T getBean(String name, Class<T> requiredType) {
        return applicationContext.getBean(name, requiredType);
    }
}
