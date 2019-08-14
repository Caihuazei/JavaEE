package com.liu.util;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DButils {
    public static <T> List<T> querAll(Class c) {
        //加载table类，反射类上的注解
        table table = (table) c.getDeclaredAnnotation(table.class);
        String className = "";
        //判断是否有注解，如果有注解，将注解属性值赋值给classname
        if (table == null) {
            className = c.getSimpleName();
        } else {
            className = table.name();
        }
        //创建查询sql语句利用反射出的name动态改变table名
        String sql = "select * from " + className;
        //创建连接，
        Connection conn = null;
        PreparedStatement psta = null;
        ResultSet rs = null;
        //创建存储信息的list集合
        List<T> list = new ArrayList<>();
        try {
            //连接数据库
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/a?useUnicode=true&characterEncoding=utf-8",
                    "root", "root");
            //执行sql语句
            psta = conn.prepareStatement(sql);
            rs = psta.executeQuery();

            while (rs.next()) {
                //将属性存储到Fields数组中
                Field[] fields = c.getDeclaredFields();
                //通过newInstance 的无参构造函数创建对象
                T t = (T) c.newInstance();
                for (Field field : fields) {
                    /**
                     * 反射出属性名，拿这个名字当数据库的字段去结果集里获取数据并且设置到对象上
                     */

                    //强制开权限 ，将私有权限公有
                    field.setAccessible(true);

                    //获取注解里的属性信息，信息代表数据库的列名
                    Column Column = field.getDeclaredAnnotation(Column.class);
                    String fieldName = "";
                    //判断是否有注解，如果有将注解名赋值给fieldname
                    if (Column == null) {
                        fieldName = field.getName();
                    } else {
                        fieldName = Column.name();
                    }
                    //给t对象设置值  field属性值
                    field.set(t, rs.getObject(fieldName));
                }
                //将t对象加载到list集合中去
                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (psta != null) {
                try {
                    psta.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return list;
    }

}
