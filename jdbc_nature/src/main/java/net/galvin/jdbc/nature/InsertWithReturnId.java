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
 * Created by Administrator on 2017/4/10.
 */
public class InsertWithReturnId {

    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://10.112.4.177:3306/test","root","123456");
            statement = connection.createStatement();
            int status = statement.executeUpdate("insert INTO t_user (name,age,sex) VALUE (\"MaoWu\",20,\"MALE\")",Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            long id = resultSet.getLong(1);
            System.out.println("id: "+id);
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            if(statement != null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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
