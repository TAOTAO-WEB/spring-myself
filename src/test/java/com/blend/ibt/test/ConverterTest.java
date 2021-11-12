package com.blend.ibt.test;

import com.blend.ibt.bean.Person;
import com.blend.ibt.converter.StringToIntegerConverter;
import com.blend.ibt.springframework.context.support.ClassPathXmlApplicationContext;
import com.blend.ibt.springframework.core.convert.converter.Converter;
import com.blend.ibt.springframework.core.convert.support.StringToNumberConverterFactory;
import org.junit.Test;

public class ConverterTest {
    @Test
    public void test_convert() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-converter.xml");
        Person husband = applicationContext.getBean("husband", Person.class);
        System.out.println("测试结果：" + husband);
    }

    @Test
    public void test_StringToIntegerConverter() {
        StringToIntegerConverter converter = new StringToIntegerConverter();
        Integer num = converter.convert("1234");
        System.out.println("测试结果：" + num);
    }

    @Test
    public void test_StringToNumberConverterFactory() {
        StringToNumberConverterFactory converterFactory = new StringToNumberConverterFactory();

        Converter<String, Integer> stringToIntegerConverter = converterFactory.getConverter(Integer.class);
        System.out.println("测试结果：" + stringToIntegerConverter.convert("1234"));

        Converter<String, Long> stringToLongConverter = converterFactory.getConverter(Long.class);
        System.out.println("测试结果：" + stringToLongConverter.convert("1234"));
    }
}
