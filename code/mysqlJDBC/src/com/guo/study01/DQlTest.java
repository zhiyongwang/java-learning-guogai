package com.guo.study01;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by guo on 2018/2/4.
 */
public class DQlTest {

    /**
     *  需求1：查询emp表中有多少条数据
     *   SQL:  SELECT COUNT(empno) FROM emp;
     *   结果集：
     *          mysql> select count(empno) from emp;
                 +--------------+
                 | count(empno) |
                 +--------------+
                 |           14 |
                 +--------------+
                 1 row in set (0.02 sec)
     */
    @Test
    public void testQueryCount() throws Exception {
        String url = "jdbc:mysql:///scott";
        String user = "root";
        String passwd= "******";    //保密
        String sql = " SELECT count(empno) FROM emp ";
        //第一步：加载注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        try(
                //第二步：获取连接对象
                Connection conn = DriverManager.getConnection(url,user,passwd);
                //第三步：获取对象语句
                Statement st = conn.createStatement();
                //第四步：执行语句，获取结果集
                ResultSet rs = st.executeQuery(sql);
                    
        ){
             if (rs.next()) {
                 int rows = rs.getInt("COUNT(empno)");
                 System.out.println(rows);     //14
             }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    //需求2：查询empno为7369的个人信息
    @Test
    public void testQuerySingle() throws ClassNotFoundException {
        String url = "jdbc:mysql:///scott";
        String user = "root";
        String passwd= "******";    //保密
        String sql = " SELECT empno,ename,job,mgr,hiredate,sal,comm,deptno FROM emp WHERE empno = 7369";
        //第一步：加载注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        try(
                //第二步：获取连接对象
                Connection conn = DriverManager.getConnection(url,user,passwd);
                //第三步：获取对象语句
                Statement st = conn.createStatement();
                //第四步：执行语句，获取结果集
                ResultSet rs = st.executeQuery(sql);

        ){
            if (rs.next()) {
                int empno = rs.getInt("empno");
                String ename = rs.getString("ename");
                String job = rs.getString("job");
                //省略后面的字段，节约时间。
                System.out.println("编号：" + empno + " 姓名："+ ename +  " 职业：" + job);   //编号：7369 姓名：SMITH 职业：CLERK
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
