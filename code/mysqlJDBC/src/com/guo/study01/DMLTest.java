package com.guo.study01;

import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Created by guo on 2018/2/4.
 * JDBC的DML操作
 */
public class DMLTest {
    //需求：向t_student表中插入：guo，24
    @Test
    public void testInsert() throws ClassNotFoundException {
        String url = "jdbc:mysql:///scott";
        String user = "root";
        String passwd= "632364";    //保密

        //第一步：加载注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        try(
                //第二步：获取连接对象
                Connection conn = DriverManager.getConnection(url, user, passwd);
                //第三步：创建语句对象
                Statement st = conn.createStatement();
        ){
            //第四步：执行SQL语句,并返回结果
            String sql = " INSERT INTO t_student (name,age) VALUE ('guo',24)";
            int i = st.executeUpdate(sql);  //插入成功返回1.
            System.out.println(i);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    //需求：把id为2的名字改为gai，25
    @Test
    public void testUpdate() throws Exception {
        //第一步：加载注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        try(
                //第二步：获取连接对象
                Connection conn = DriverManager.getConnection("jdbc:mysql:///scott","root","******");
                //第三步：获取对象语句
                Statement st = conn.createStatement();
                ){
            //第四步：执行语句
            String sql = " UPDATE t_student SET name = 'gai',age = 25 WHERE id = 2 ";
            int i = st.executeUpdate(sql);
            System.out.println(i);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    //需求：删除Id为2的学生
    @Test
    public void testDelete() throws ClassNotFoundException {
        String url = "jdbc:mysql:///scott";
        String user = "root";
        String passwd= "******";    //保密
        //第一步：加载注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        try(
                //第二步：获取连接对象
                Connection conn = DriverManager.getConnection(url,user,passwd);
                //第三步：获取对象语句
                Statement st = conn.createStatement();
        ){
            //第四步：执行语句
            String sql = " DELETE FROM t_student WHERE id = 2 ";
            int i = st.executeUpdate(sql);
            System.out.println(i);

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
