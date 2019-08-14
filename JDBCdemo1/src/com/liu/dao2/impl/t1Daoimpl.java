package com.liu.dao2.impl;

import com.liu.dao2.DBUtil;
import com.liu.dao2.DaoT1;
import com.liu.demo1.Content;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class t1Daoimpl implements DaoT1 {
    @Override
    public int addvalue(String name) {
        DBUtil dbUtil = new DBUtil();
        int row = 0;
        try {

            PreparedStatement psta = dbUtil.GetConn().prepareStatement("insert into t1 (name) values(?)");
            psta.setString(1,name);
            row = psta.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int deleteByid(int id) {
        DBUtil dbUtil = new DBUtil();
        int row = 0;
        try {

            PreparedStatement psta = dbUtil.GetConn().prepareStatement("delete from t1 where id = ?");
            psta.setInt(1,id);
            System.out.println(psta);
            row = psta.executeUpdate();
            if (row > 0){
                System.out.println("成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public int updateByid( String set, int id) {
        DBUtil dbUtil = new DBUtil();
        int row = 0;
        try {

            PreparedStatement psta = dbUtil.GetConn().prepareStatement("update t1 set name = ? where id = ?");
            psta.setString(1,set);
            psta.setInt(2,id);
            System.out.println(psta);
            row = psta.executeUpdate();
            if (row > 0){
                System.out.println("成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public void select() {
        DBUtil dbUtil = new DBUtil();
        int row = 0;
        try {
            PreparedStatement psta = dbUtil.GetConn().prepareStatement("select * from t1");
//            System.out.println(psta);
            ResultSet rs = psta.executeQuery();
            List<Content> contentList = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Content content = new Content(id,name);
                contentList.add(content);
//                System.out.println(rs.getInt("id") + rs.getString("name"));
            }
            for (Content content : contentList) {
                System.out.println(content);
            }
            if (row > 0){
                System.out.println("成功");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
