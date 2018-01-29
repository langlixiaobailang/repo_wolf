package com.cmos.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan({"com.cmos.web.dao"})
@ComponentScan(basePackages={"com.cmos.web"})
@EnableCaching
//@ServletComponentScan
//@EnableAspectJAutoProxy//开启spring对Aspectj的支持
public class SpingBootApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(SpingBootApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringBootApplication.class);
	}
}
