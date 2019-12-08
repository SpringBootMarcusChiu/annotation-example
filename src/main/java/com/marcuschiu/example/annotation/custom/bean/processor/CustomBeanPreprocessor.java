package com.marcuschiu.example.annotation.custom.bean.processor;

import com.marcuschiu.example.annotation.custom.bean.processor.callbacks.CustomFieldCallback;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

@Component
public class CustomBeanPreprocessor implements BeanPostProcessor {

    private ConfigurableListableBeanFactory configurableBeanFactory;

    @Autowired
    public CustomBeanPreprocessor(ConfigurableListableBeanFactory beanFactory) {
        this.configurableBeanFactory = beanFactory;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> managedBeanClass = bean.getClass();

        // Field Callback
        ReflectionUtils.FieldCallback fieldCallback = new CustomFieldCallback(configurableBeanFactory, bean);
        ReflectionUtils.doWithFields(managedBeanClass, fieldCallback);

        // Method Callback
//        ReflectionUtils.MethodCallback methodCallback = new CustomAnnotationMethodCallBack(configurableBeanFactory, bean);
//        ReflectionUtils.doWithMethods(managedBeanClass, methodCallback);

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}