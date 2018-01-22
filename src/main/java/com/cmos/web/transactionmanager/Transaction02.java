//package com.cmos.web.transactionmanager;
//
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.aop.Advisor;
//import org.springframework.aop.aspectj.AspectJExpressionPointcut;
//import org.springframework.aop.support.DefaultPointcutAdvisor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.TransactionDefinition;
//import org.springframework.transaction.interceptor.*;
//
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by Administrator on 2018/1/19.
// */
//@Aspect
//@Configuration
//public class Transaction02 {
//
//    private  static final int  TX_METHOD_TIMEOUT = 5;
//    private static final String AOP_POINTCUT_EXPRESSION = "execution (* com.cmos.web..serviceimpl.*.*(..))";
//    @Autowired
//    private PlatformTransactionManager transactionManager;
//
//    @Bean
//    public  TransactionInterceptor txAdvice(){
//
//        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
//
//        // 只读事务，不做更新操作
//        RuleBasedTransactionAttribute readOnlyTx = new RuleBasedTransactionAttribute();
//        readOnlyTx.setReadOnly(true);
//        // PROPAGATION_NOT_SUPPORTED Spring不会执行事务中的代码。代码总是在非事务环境下执行，如果当前有事务，则该事务挂起
//        readOnlyTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);
//        // 当前存在事务就使用当前事务，不存在就创建一个新的事务
//        RuleBasedTransactionAttribute requiredTx = new RuleBasedTransactionAttribute();
//        requiredTx.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
//        // PROPAGATION_REQUIRED 当前如果有事务，Spring就会使用该事务；否则会开始一个新事务,
//        requiredTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
//        requiredTx.setTimeout(TX_METHOD_TIMEOUT);
//        Map<String, TransactionAttribute> txMap = new HashMap<>();
//        txMap.put("add*", requiredTx);
//        txMap.put("save*", requiredTx);
//        txMap.put("insert*", requiredTx);
//        txMap.put("update*", requiredTx);
//        txMap.put("delete*", requiredTx);
//        txMap.put("get*", readOnlyTx);
//        txMap.put("query*", readOnlyTx);
//        source.setNameMap( txMap );
//        TransactionInterceptor txAdvice = new TransactionInterceptor(transactionManager, source);
//        return txAdvice;
//
//
//    }
//
//
//
//    @Bean
//    public Advisor txAdviceAdvisor() {
//        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
//        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
//        return new DefaultPointcutAdvisor(pointcut, txAdvice());
//        //return new DefaultPointcutAdvisor(pointcut, txAdvice);
//     }
//}
