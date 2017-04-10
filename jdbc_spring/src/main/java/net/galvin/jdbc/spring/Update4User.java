package net.galvin.jdbc.spring;

import com.alibaba.druid.pool.DruidDataSource;
import net.galvin.jdbc.comm.pojo.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/10.
 */
public class Update4User {

    private static NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public static void main(String[] args) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://10.112.4.177:3306/test");
        dataSource.setPassword("123456");
        dataSource.setUsername("root");
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("age",100);
        namedParameterJdbcTemplate.update("update t_user tu set tu.age = :age where tu.id = 1",params);
    }

}
