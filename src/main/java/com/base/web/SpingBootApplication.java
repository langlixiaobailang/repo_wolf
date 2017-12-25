package com.base.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.base.web.dao")
public class SpingBootApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpingBootApplication.class, args);
	}
}
