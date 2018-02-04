package com.guo.study01;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by guo on 2018/2/4.
 * 创建一张表和异常处理
 */
public class DDLAndException {
    /**
     * 创建一张表
      */
    @Test
    public void createTable() {
        try{
            //第一步：加载注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //第二步：获取连接对象
            String url = "jdbc:mysql://localhost:3306/scott";
            String user = "root";
            String passwd= "******";    //保密
            Connection conn = DriverManager.getConnection(url,user,passwd);
            //第三步：创建/获取语句对象 ,SQL语句之前之后最好加上空格
            String sql = " CREATE TABLE `t_student` (`id` bigint(20) PRIMARY KEY AUTO_INCREMENT,`name` varchar(20),`age` int(11)) ";
            Statement st = conn.createStatement();
            //第四步：执行语句
            int i = st.executeUpdate(sql);
            System.out.println(i);
            //第五步：释放资源
            st.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //如何正确的处理JDBC异常
    @Test
    public void handlerException() {
            Connection conn = null;     //连接对象
            Statement  st = null;       //对象语句

            String url = "jdbc:mysql://localhost:3306/scott";
            String user = "root";
            String passwd= "******";    //保密
        try{
            //第一步：加载注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //第二步：获取连接对象
            conn = DriverManager.getConnection(url, user, passwd);
            //第三步：创建语句对象
            st = conn.createStatement();
            //第四步：执行SQL语句,并返回结果
            String sql = " CREATE TABLE `t_student` (`id` bigint(20) PRIMARY KEY AUTO_INCREMENT,`name` varchar(20),`age` int(11)) ";
            int i = st.executeUpdate(sql);

        }catch(Exception e) {
               e.printStackTrace();
        } finally {
            //第五步：释放资源
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    if (conn != null) {
                        try{
                            conn.close();
                        }catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }
    }
    //使用JDK7的自动资源关闭
    @Test
    public void testHandlerExceptionJava7() throws ClassNotFoundException {

        String url = "jdbc:mysql://localhost:3306/scott";
        String user = "root";
        String passwd= "******";    //保密

        //第一步：加载注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        try(
            //第二步：获取连接对象
            Connection conn = DriverManager.getConnection(url, user, passwd);
            //第三步：创建语句对象
            Statement st = conn.createStatement();
        ){
            //第四步：执行SQL语句,并返回结果
            String sql = " CREATE TABLE `t_student` (`id` bigint(20) PRIMARY KEY AUTO_INCREMENT,`name` varchar(20),`age` int(11)) ";
            int i = st.executeUpdate(sql);
            System.out.println(i);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
