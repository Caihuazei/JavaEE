import com.liu.entity.Order;
import com.liu.mapper.OrderMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class OrderTest {
    public static void main(String[] args) {
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(
                OrderMapper.class.getClassLoader().getResourceAsStream("mybatis.xml")
        );
        SqlSession session = factory.openSession(true);

        OrderMapper orderMapper = session.getMapper(OrderMapper.class);

        for (Order order : orderMapper.listOrder()) {
            System.out.println(order);

        }
    }
}
