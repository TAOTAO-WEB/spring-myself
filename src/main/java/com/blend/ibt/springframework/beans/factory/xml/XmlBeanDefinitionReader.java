package com.blend.ibt.springframework.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import com.blend.ibt.springframework.beans.BeansException;
import com.blend.ibt.springframework.beans.PropertyValue;
import com.blend.ibt.springframework.beans.factory.config.BeanDefinition;
import com.blend.ibt.springframework.beans.factory.config.BeanReference;
import com.blend.ibt.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import com.blend.ibt.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.blend.ibt.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import com.blend.ibt.springframework.core.io.Resource;
import com.blend.ibt.springframework.core.io.ResourceLoader;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 对xml文件的解析
 * @author tt
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }


    protected void doLoadBeanDefinitions(InputStream in) throws ClassNotFoundException, DocumentException {
        SAXReader reader = new SAXReader();
        Document doc = reader.read(in);
        Element root = doc.getRootElement();

        //解析context:component-scan标签，扫描包中的类并提供相关信息，用于组装BeanDefinition
        Element componentScan = root.element("component-scan");
        if(null!=componentScan){
            String scanPath = componentScan.attributeValue("base-package");
            if(StrUtil.isEmpty(scanPath)){
                throw new BeansException("The value of base-package attribute can not be empty or null");
            }
            scanPackage(scanPath);
        }

        List<Element> beanList = root.elements("bean");
        for(Element bean:beanList){

            //解析标签
            String id = bean.attributeValue("id");
            String name = bean.attributeValue("name");
            String className = bean.attributeValue("class");
            String initMethod = bean.attributeValue("init-method");
            String destroyMethod = bean.attributeValue("destroy-method");
            String beanScope = bean.attributeValue("scope");

            //获取class，方便获取类中的名称
            Class<?> clazz = Class.forName(className);
            // 优先级 id>name
            String beanName = StrUtil.isNotEmpty(id)?id:name;
            if(StrUtil.isEmpty(beanName)){
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }

            //定义Bean
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            beanDefinition.setInitMethodName(initMethod);
            beanDefinition.setDestroyMethodName(destroyMethod);

            if(StrUtil.isNotEmpty(beanScope)){
                beanDefinition.setScope(beanScope);
            }

            List<Element> propertyList = bean.elements("property");
            //读取属性并填充
            for(Element property:propertyList){
                // 解析标签 property
                String attrName = property.attributeValue("name");
                String attrValue = property.attributeValue("value");
                String attrRef = property.attributeValue("ref");
                //获取属性值
                Object value = StrUtil.isNotEmpty(attrRef)?new BeanReference(attrRef):attrValue;

                //创建属性信息
                PropertyValue propertyValue = new PropertyValue(attrName,value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
            if(getRegistry().containsBeanDefinition(beanName)){
                throw new BeansException("Duplicate beanName["+beanName+"] is not allowed");
            }
            //注册BeanDefinition
            getRegistry().registerBeanDefinition(beanName,beanDefinition);
        }
    }

    private void scanPackage(String scanPath){
        String[] basePackages = StrUtil.splitToArray(scanPath,',');
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(getRegistry());
        scanner.doScan(basePackages);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try{
            try(InputStream inputStream = resource.getInputStream()){
                doLoadBeanDefinitions(inputStream);
            }
        }catch (IOException | ClassNotFoundException | DocumentException e) {
            throw new BeansException("IOException parsing XML document from "+resource,e);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for(Resource resource:resources){
            loadBeanDefinitions(resource);
        }
    }


    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    @Override
    public void loadBeanDefinitions(String... locations) throws BeansException {
        for (String location : locations) {
            loadBeanDefinitions(location);
        }
    }


}
