package com.marcuschiu.example.annotation;

import com.marcuschiu.example.annotation.annotation.CustomAnnotation;
import com.marcuschiu.example.annotation.model.RandomGeneric;
import com.marcuschiu.example.annotation.model.pojo.RandomPojoA;
import com.marcuschiu.example.annotation.model.pojo.RandomPojoB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class AnnotationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnnotationApplication.class, args);
	}

	@CustomAnnotation
    private RandomGeneric<RandomPojoA> aGeneric;

	@CustomAnnotation
    private RandomGeneric<RandomPojoB> bGeneric;

	@PostConstruct
	public void postConstruct() {
		System.out.println("LOG: aGeneric entityClassName: " + aGeneric.getEntityClassName());
		System.out.println("LOG: bGeneric entityClassName: " + bGeneric.getEntityClassName());
	}
}
