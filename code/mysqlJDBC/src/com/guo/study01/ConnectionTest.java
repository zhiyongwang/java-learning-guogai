package com.guo.study01;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by guo on 2018/2/4.
 *JDBC操作第一步：获取JDBC连接对象/Connection对象
 * 操作步骤：
 *      1、加载注册驱动
 *              Class.forName("com.mysql.jdbc.Driver")
 *      2、获取连接对象
 *      3、获取SQL语句
 *      4、编译执行
 *      5、释放资源
 */
public class ConnectionTest {

    @Test
    public void testGetConnetction() {
        try {
            //第一步：加载注册驱动
            Class.forName("com.mysql.jdbc.Driver");

            //第二步：获取连接对象Connection
            String url = "jdbc:mysql://localhost:3306/scott";
            String user = "root";
            String passwd= "******";    //保密
            //调用DriverManager类中的getConnection方法获取连接，返回Connection连接对象
            Connection conn = DriverManager.getConnection(url,user,passwd);
            //可以使用show processlist 查看连接
            System.out.println(conn);

        } catch ( Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 从javaJDK 6 开始：JDBC4.0新增了一个特性无需加载驱动，直接获取连接。
     * 规范要求：从JDBC4.0开始所有的驱动都必须包括 META-INF/service/java.sql.Driver文件。
     *          此文件包含java.sql.Driver的驱动程序实现的名称：com.mysql.jdbc.Driver
     *          程序会自动从此文件中读取当前驱动类的全限定名。
     *
     * 在Web开发中，还是得使用手动加载驱动的方式。
     *        
     * The DriverManager methods getConnection and getDrivers have been enhanced to support the Java Standard Edition Service Provider mechanism.
     * JDBC 4.0 Drivers must include the file META-INF/services/java.sql.Driver. This file contains the name of the JDBC drivers implementation of java.sql.Driver.
     * For example, to load the my.sql.Driver class, the META-INF/services/java.sql.Driver file would contain the entry:
     */
    @Test
    public void testGetConnetction1() {
        try {
            //第一步：获取连接对象Connection
            String url = "jdbc:mysql://localhost:3306/scott";
            String user = "root";
            String passwd= "******";    //保密
            //调用DriverManager类中的getConnection方法获取连接，返回Connection连接对象
            Connection conn = DriverManager.getConnection(url,user,passwd);
            //可以使用show processlist 查看连接
            System.out.println(conn);

        } catch ( Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *   -----------------------------------------------------------
     oracle
     driverClass：oracle.jdbc.driver.OracleDriver
     url：jdbc:oracle:thin:@127.0.0.1:1521:dbname

     -----------------------------------------------------------
     mysql
     driverClass：com.mysql.jdbc.Driver
     url：jdbc:mysql://localhost:3306/mydb

     -----------------------------------------------------------
     PS：有的时候，mysql的驱动类也也会看到使用org.gjt.mm.mysql.Driver的情况，
     org.gjt.mm.mysql.Driver是早期的驱动名称，后来就改名为com.mysql.jdbc.Driver，
     现在一般都推荐使用 com.mysql.jdbc.Driver。在最新版本的mysql jdbc驱动中，为了保持对老版本的兼容，    仍然保留了org.gjt.mm.mysql.Driver，但是实际上 org.gjt.mm.mysql.Driver中调用   了com.mysql.jdbc.Driver，因此现在这两个驱动没有什么区别。

     -----------------------------------------------------------
     DB2
     driverClass：com.ibm.db2.jcc.DB2Driver
     url：jdbc:db2://127.0.0.1:50000/dbname
     -----------------------------------------------------------
     syBase
     driverClass：com.sybase.jdbc.SybDriver
     url：jdbc:sybase:Tds:localhost:5007/dbname
     -----------------------------------------------------------
     PostgreSQL
     driverClass：org.postgresql.Driver
     url：jdbc:postgresql://localhost/dbname
     -----------------------------------------------------------
     Sql Server2000
     driverClass：com.microsoft.jdbc.sqlserver.SQLServerDriver
     url：jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=dbname
     -----------------------------------------------------------
     Sql Server2005
     driverClass：com.microsoft.sqlserver.jdbc.SQLServerDriver
     url：jdbc:sqlserver://localhost:1433; DatabaseName=dbname
     -----------------------------------------------------------
     */

}
