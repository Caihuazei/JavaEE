package com.liu.mapper;

import com.liu.entity.Order;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

public class OrderMapperTest {

    SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(
            OrderMapper.class.getClassLoader().getResourceAsStream("mybatis.xml")
    );
    SqlSession session = factory.openSession(true);
    OrderMapper orderMapper = session.getMapper(OrderMapper.class);

    @Test
    public void listOrderTest(){

        for (Order order : orderMapper.listOrder()) {
            System.out.println(order);
        }
    }
}
