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
 *     1. 只执行查询的sql，不能执行 update insert delete。
 *     2. 该方法不能被 PreparedStatement 和 CallableStatement 执行。
 *     3. 返回值 ResultSet 包含 查询语句返回的数据。返回值不可能为空。
 *
 * int executeUpdate(String sql) throws SQLException;
 *     1. 只执行 update delete insert 的sql，或者 ddl语句。
 *     2. 该方法不能被 PreparedStatement 和 CallableStatement 执行。
 *     3. 返回值：执行DML语句，影响表中的数据的行数。
 *     4. 返回值：无返回值的sql，返回0。
 *
 * void close() throws SQLException;
 *     1. 立即释放 Statement 对象的 数据库和jdbc资源。而不是等到它自动关闭。
 *     2. 可以重复执行该方法。
 *
 * boolean execute(String sql) throws SQLException;
 *     1. 该方法可以执行所有的sql语句，select update insert delete。
 *     2. 该方法不能被 PreparedStatement 和 CallableStatement 执行。
 *     3. 返回值：true。执行的sql返回多个结果值，这个时候需要调用 ResultSet getResultSet() throws SQLException;
 *     4. 返回值：false。执行的sql返回的是 更新行数或者无返回结果，这个时候需要调用 int getUpdateCount() throws SQLException;
 *
 * void addBatch( String sql ) throws SQLException;
 *     1. Statement对象维护了一个命令列表，该方法会把sql命令加到这个命令列表。调用 executeBatch 可以批量执行这些sql。
 *     2. 该方法不能被 PreparedStatement 和 CallableStatement 执行。
 *     3. 只能添加 insert 和 update 命令。
 *
 * int[] executeBatch() throws SQLException;
 *     1. 提交一个批量执行的命令给数据库，如果所有的命令执行成功，会返回一个 update counts 的数组。
 *     2. update counts 数组的顺序和添加sql命令的顺序一一对应。
 *     3. 数组中元素的值,
 *              1. >=0: 命令执行成功，代表表中影响的行数。
 *              2. SUCCESS_NO_INFO：命令执行成功，但是不知道影响了多少行。
 *              3. 如果有一个sql命令执行失败，该方法会抛出异常：BatchUpdateException。jdbc可能会执行也有可能不会执行剩下的命令。
 *
 * 接口 [ java.sql.Statement ]  ------  end
 *
 */
