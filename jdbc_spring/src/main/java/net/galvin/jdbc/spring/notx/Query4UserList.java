package net.galvin.jdbc.spring.notx;

import com.alibaba.druid.pool.DruidDataSource;
import net.galvin.jdbc.comm.pojo.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/10.
 */
public class Query4UserList {

    private static JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://10.112.4.177:3306/test");
        dataSource.setPassword("123456");
        dataSource.setUsername("root");
        jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        List<User> userMap =
                jdbcTemplate.query("SELECT * FROM t_user", new RowMapper<User>() {
                    @Override
                    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                        long id = resultSet.getLong("id");
                        String name = resultSet.getString("name");
                        int age = resultSet.getInt("age");
                        String sex = resultSet.getString("sex");
                        Date createTime = resultSet.getDate("create_time");
                        Date updateTime = resultSet.getDate("update_time");
                        User user = new User();
                        user.setId(id);
                        user.setName(name);
                        user.setAge(age);
                        user.setSex(sex);
                        user.setCreateTime(createTime);
                        user.setUpdateTime(updateTime);
                        return user;
                    }
                });
        System.out.println(userMap);
    }

}
