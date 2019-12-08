package com.marcuschiu.example.annotation.model;

public class RandomGeneric<E> {

    private Class<E> entityClass;

    public RandomGeneric(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    public String getEntityClassName() {
        return entityClass.getName();
    }
}
