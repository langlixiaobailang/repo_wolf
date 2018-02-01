//package com.cmos.web.transactionmanager;
//
//import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.interceptor.TransactionInterceptor;
//
//import java.util.Properties;
//
///**
// * Created by Administrator on 2018/1/19.
// */
////@Configuration
//@Component
//public class Transaction {
//
//    @Autowired
//    private DataSourceTransactionManager transactionManager;
//    // 创建事务通知
//    @Bean(name = "txAdvice")
//    public TransactionInterceptor getAdvisor() throws Exception {
//        Properties properties = new Properties();
//        properties.setProperty("get*", "PROPAGATION_REQUIRED,-Exception");
//        properties.setProperty("add*", "PROPAGATION_REQUIRED,-Exception");
//        properties.setProperty("save*", "PROPAGATION_REQUIRED,-Exception");
//        properties.setProperty("update*", "PROPAGATION_REQUIRED,-Exception");
//        properties.setProperty("delete*", "PROPAGATION_REQUIRED");
//        TransactionInterceptor tsi = new TransactionInterceptor(transactionManager,properties);
//        return tsi;
//    }
//    @Bean
//    public BeanNameAutoProxyCreator txProxy() {
//        BeanNameAutoProxyCreator creator = new BeanNameAutoProxyCreator();
//        creator.setInterceptorNames("txAdvice");
//        creator.setBeanNames("*SV", "*Impl","*impl");
//        creator.setProxyTargetClass(true);
//        return creator;
//    }
//}
