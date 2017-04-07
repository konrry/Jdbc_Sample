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
public class InsertSimple {

    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://10.112.4.177:3306/test","root","123456");
            statement = connection.createStatement();
            statement.execute("insert INTO t_user (name,age,sex) VALUE (\"MaoYi\",20,\"MALE\")");
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