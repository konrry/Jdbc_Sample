package net.galvin.jdbc.spring.tx;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.Properties;

/**
 * Created by Administrator on 2017/4/10.
 */
public class UserTransactionDemo {

    public static void main(String[] args) throws ClassNotFoundException {

        //初始化数据源
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://10.112.4.177:3306/test");
        dataSource.setPassword("123456");
        dataSource.setUsername("root");
        dataSource.setDefaultAutoCommit(false);

        //初始化事务管理器
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource);

        //初始化拦截器
        TransactionInterceptor transactionInterceptor = new TransactionInterceptor();
        transactionInterceptor.setTransactionManager(dataSourceTransactionManager);
        Properties transactionAttributes = new Properties();
        transactionAttributes.put("insert*","PROPAGATION_REQUIRED,-Exception");
        transactionInterceptor.setTransactionAttributes(transactionAttributes);

        //初始化JdbcTemplate
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        UserDao userDao = UserDaoImpl.get(jdbcTemplate);

        //初始化代理工厂
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setProxyInterfaces(new Class[]{UserDao.class});
        proxyFactoryBean.setTarget(userDao);
        proxyFactoryBean.addAdvice(transactionInterceptor);

        //获取代理类
        UserDao userDaoProxy = (UserDao) proxyFactoryBean.getObject();

        //调用使用事务的方法
        userDaoProxy.insert(10);

    }

}
