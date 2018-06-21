package com.cmos.web.common.transactionmanager;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Administrator on 2018/1/19.
 */
@Configuration
@Component
public class TransactionCommon {

    @Autowired(required = false)
    public DataSourceTransactionManager transactionManager;
    @Autowired
    DataSource dataSource;
    // 创建事务通知
    @Bean(name = "txAdvice")
    public TransactionInterceptor getAdvisor() throws Exception {
        Properties properties = new Properties();
        properties.setProperty("get*", "PROPAGATION_REQUIRED,readOnly");
        properties.setProperty("select*", "PROPAGATION_REQUIRED,readOnly");
        properties.setProperty("count*", "PROPAGATION_REQUIRED,readOnly");
        properties.setProperty("add*", "PROPAGATION_REQUIRED,-Throwable");
        properties.setProperty("save*", "PROPAGATION_REQUIRED,-Throwable");
        properties.setProperty("update*", "PROPAGATION_REQUIRED,-Throwable");
        properties.setProperty("delete*", "PROPAGATION_REQUIRED,-Throwable");

        TransactionInterceptor tsi = new TransactionInterceptor(transactionManager,properties);
        return tsi;
    }
    @Bean
    public BeanNameAutoProxyCreator txProxy() {
        BeanNameAutoProxyCreator creator = new BeanNameAutoProxyCreator();
        creator.setInterceptorNames("txAdvice");
        creator.setBeanNames("*SV", "*Impl","*impl");
        creator.setProxyTargetClass(true);
        return creator;
    }
}
