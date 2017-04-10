package net.galvin.jdbc.nature;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

/**
 * Created by Administrator on 2017/4/10.
 */
public class Manipulation4Tx {

    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;
        Savepoint savepoint1 = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://10.112.4.177:3306/test","root","123456");
            statement = connection.createStatement();
            connection.setAutoCommit(false);
            int status = statement.executeUpdate("insert INTO t_user (name,age,sex) VALUE (\"MaoShi\",20,\"MALE\")",Statement.RETURN_GENERATED_KEYS);
            savepoint1 = connection.setSavepoint("savepoint1");
            int a = 0;
            int b = 1;
            status = statement.executeUpdate("insert INTO t_user (name,age,sex) VALUE (\"MaoShiYi\",20,\"MALE\")",Statement.RETURN_GENERATED_KEYS);
//            int c = b/a;
        } catch (Exception e){
            e.printStackTrace();
            if(savepoint1 != null){
                try {
                    connection.rollback(savepoint1);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }else {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }

        }finally {
            try {
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
