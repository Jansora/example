package com.jansora.overbean.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.List;


@Configuration
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class OverBeanConfig implements BeanFactoryPostProcessor {

//    @Autowired
//    AppProperties appProperties;


    public void doExcludeBeans(DefaultListableBeanFactory factory) {
        if (ObjectUtils.isEmpty(factory)) {
            return;
        }

        List<String> beans = Arrays.asList(
                "com.jansora.overbean.service.impl.custom.Korean",
                "com.jansora.overbean.service.impl.product.Asian",
                "com.jansora.overbean.service.impl.product.European", "a"
        );

        for (String className: beans) {
            try {
                Class<?> clazz = Class.forName(className);
                String[] beanNamesForClazz = factory.getBeanNamesForType(clazz);

                for (String beanName: beanNamesForClazz) {
                    System.out.println("remove bean from springContext. beanName: " + beanName);
                    factory.removeBeanDefinition(beanName);
                }
            }
            catch (ClassNotFoundException e) {
                System.out.println(e);
            }
            catch (NoSuchBeanDefinitionException f) {
                System.out.println("can't find bean of " + className);
            }
        }

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
                doExcludeBeans((DefaultListableBeanFactory) beanFactory);
    }
}
