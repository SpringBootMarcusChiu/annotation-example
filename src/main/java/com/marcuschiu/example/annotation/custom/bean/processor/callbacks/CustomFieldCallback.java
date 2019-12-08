package com.marcuschiu.example.annotation.custom.bean.processor.callbacks;

import com.marcuschiu.example.annotation.annotation.CustomAnnotation;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class CustomFieldCallback implements ReflectionUtils.FieldCallback {

    private ConfigurableListableBeanFactory configurableBeanFactory;
    private Object bean;

    public CustomFieldCallback(ConfigurableListableBeanFactory bf, Object bean) {
        configurableBeanFactory = bf;
        this.bean = bean;
    }

    @Override
    public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
        if (field.isAnnotationPresent(CustomAnnotation.class)) {
            ReflectionUtils.makeAccessible(field);

            // getting @CustomAnnotations element values
//            Class<?> genericClass = field.getDeclaredAnnotation(CustomAnnotation.class).value();

            Type fieldGenericType = field.getGenericType();
            if (fieldGenericType instanceof ParameterizedType) {
                ParameterizedType pType = (ParameterizedType) fieldGenericType;
                Class<?> fieldClass = (Class<?>) pType.getRawType();
                Class<?> genericClass = (Class<?>) pType.getActualTypeArguments()[0];

                String beanName = genericClass.getSimpleName() + fieldClass.getSimpleName();
                Object beanInstance = getBeanInstance(beanName, fieldClass, genericClass);
                field.set(bean, beanInstance);
            }
        }
    }

    private Object getBeanInstance(String beanName, Class<?> genericClass, Class<?> paramClass) {
        Object o;
        if (!configurableBeanFactory.containsBean(beanName)) {

            Object toRegister;
            try {
                Constructor<?> ctr = genericClass.getConstructor(Class.class);
                toRegister = ctr.newInstance(paramClass);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            o = configurableBeanFactory.initializeBean(toRegister, beanName);
            configurableBeanFactory.autowireBeanProperties(o, AutowireCapableBeanFactory.AUTOWIRE_BY_NAME, true);
            configurableBeanFactory.registerSingleton(beanName, o);
        } else {
            o = configurableBeanFactory.getBean(beanName);
        }
        return o;
    }
}