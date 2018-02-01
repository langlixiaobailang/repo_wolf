package com.cmos.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan({"com.cmos.web.dao.sys"})
@ComponentScan(basePackages={"com.cmos.web"})
@EnableCaching
@EnableTransactionManagement
@EnableAspectJAutoProxy//开启spring对Aspectj的支持
//@ServletComponentScan
public class SpingBootApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpingBootApplication.class, args);
	}

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		return builder.sources(SpringBootApplication.class);
//	}
}
