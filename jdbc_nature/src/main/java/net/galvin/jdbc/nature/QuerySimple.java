package net.galvin.jdbc.nature;

import net.galvin.jdbc.comm.pojo.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/4/7.
 */
public class QuerySimple {

    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;
        try {

            //1 增加jdbc的依赖包
            //2 加载驱动类
            Class.forName("com.mysql.jdbc.Driver");
            //3 建立连接： 地址、用户名、密码
            connection = DriverManager.getConnection("jdbc:mysql://10.112.4.177:3306/test","root","123456");
            //4 建立statement
            statement = connection.createStatement();
            //5 执行SQL
            statement.execute("SELECT * FROM t_user");
            //6 获取ResultSet
            ResultSet resultSet = statement.getResultSet();
            List<User> userList = new ArrayList<User>();
            while (resultSet.next()){
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
                userList.add(user);
            }
            System.out.println(userList);
        } catch (Exception e){
            e.printStackTrace();
        }finally {

            //7 关闭statement
            if(statement != null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            //8 关闭connection
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}


/**
 *
 * 接口 [ java.sql.Statement ]  ------  start
 *
 * ResultSet executeQuery(String sql) throws SQLException;
 *
 *
 *
 *[ java.sql.Statement ]  ------  end
 *
 *
 *
 */
