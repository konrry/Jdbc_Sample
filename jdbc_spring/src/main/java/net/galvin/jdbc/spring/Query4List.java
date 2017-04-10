package net.galvin.jdbc.spring;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/10.
 */
public class Query4List {

    private static JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://10.112.4.177:3306/test");
        dataSource.setPassword("123456");
        dataSource.setUsername("root");
        jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        List<Map<String,Object>> userMap = jdbcTemplate.queryForList("SELECT * FROM t_user");
        System.out.println(userMap);
    }

}
