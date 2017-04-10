package net.galvin.jdbc.spring.tx;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * Created by Administrator on 2017/4/10.
 */
public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;

    public static UserDaoImpl UserDaoImpl = null;

    public static UserDaoImpl get(JdbcTemplate jdbcTemplate){
        if(UserDaoImpl == null){
            synchronized (UserDaoImpl.class){
                if(UserDaoImpl == null){
                    UserDaoImpl = new UserDaoImpl(jdbcTemplate);
                }
            }
        }
        return UserDaoImpl;
    }

    private UserDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    public int insert(int age){
        System.out.println("Has Transation: "+TransactionSynchronizationManager.isActualTransactionActive());
        String sql = "insert INTO t_user (name,age,sex) VALUE (\'MaoYi\',"+age+",\'MALE\')";
        return this.jdbcTemplate.update(sql);
    }

}
