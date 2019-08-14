package com.liu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class test {

    public static void main(String[] args) {
        Connection conn = null;
        Statement sta = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/a?useUnicode=true&characterEncoding=utf-8","root","root");
            sta = conn.createStatement();
            String sql = "insert into t1 (name) values('刘')";
            int count = sta.executeUpdate(sql);
            if (count > 0 ){
                System.out.println("成功");
            }else {
                System.out.println("失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
