package net.galvin.jdbc.spring;

import com.alibaba.druid.pool.DruidDataSource;
import net.galvin.jdbc.comm.pojo.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/4/10.
 */
public class Query4BeanPropertyRowMapper {

    private static JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://10.112.4.177:3306/test");
        dataSource.setPassword("123456");
        dataSource.setUsername("root");
        jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        List<User> userMap =
                jdbcTemplate.query("SELECT * FROM t_user", new BeanPropertyRowMapper<User>(User.class));
        System.out.println(userMap);
    }

}
