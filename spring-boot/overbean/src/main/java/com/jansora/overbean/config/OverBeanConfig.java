package com.jansora.overbean.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;


@Configuration
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class OverBeanConfig implements BeanFactoryAware, ApplicationContextAware, BeanFactoryPostProcessor {

//    @Autowired
//    AppProperties appProperties;

//    BeanFactory beanFactory;

    @PostConstruct
    public void init() {
//        doExcludeBeans((DefaultListableBeanFactory) beanFactory);
    }


    public void doExcludeBeans(DefaultListableBeanFactory factory) {
        if (ObjectUtils.isEmpty(factory)) {
            return;
        }
//        appProperties = factory.getBean(AppProperties.class);
//        if (ObjectUtils.isEmpty(factory) || ObjectUtils.isEmpty(appProperties) || ObjectUtils.isEmpty(appProperties.getExcludeBeanClasses())) {
//            return;
//        }
        List<String> beans = Arrays.asList(
                "com.jansora.overbean.service.impl.custom.Korean",
                "com.jansora.overbean.service.impl.product.Asian",
                "com.jansora.overbean.service.impl.product.European"
        );

        for (String className: beans) {
            try {
                Class<?> clazz = Class.forName(className);
                String[] beanNamesForClazz = factory.getBeanNamesForType(clazz);

                for (String beanName: beanNamesForClazz) {
                    System.out.println("remove bean from springContext. beanName: " + beanName);
//                    factory.destroySingleton(beanName);
//                    factory.destroySingletons();
                    factory.removeBeanDefinition(beanName);
                }
            }
            catch (ClassNotFoundException e) {
                System.out.println(e);
            }
            catch (BeansException f) {
                System.out.println("can't find bean of " + className);
            }
        }

    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
//        this.beanFactory = beanFactory;
//        doExcludeBeans((DefaultListableBeanFactory) beanFactory);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        doExcludeBeans((DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory());
        System.out.println("");
    }

//    @Override
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        Object result = BeanFactoryPostProcessor.super.postProcessAfterInitialization(bean, beanName);
////        doExcludeBeans((DefaultListableBeanFactory) beanFactory);
//        return result;
//    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
                doExcludeBeans((DefaultListableBeanFactory) beanFactory);
    }
}
