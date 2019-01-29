import com.mybatisplustest.beans.Good_Employee;
import com.mybatisplustest.mapper.GoodEmployeeMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @program: mybatisplus-EmployeeMapperTest
 * @description: 测试
 * @author: Mr.Ning
 * @create: 2019-01-29 10:01
 **/

public class GoodEmployeeMapperTest {
    private ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
    private GoodEmployeeMapper goodEmployeeMapper = ioc.getBean("goodEmployeeMapper",GoodEmployeeMapper.class);

    //测试连接DB
    @Test
    public void testDataSource() throws Exception {
        DataSource ds  = ioc.getBean("dataSource",DataSource.class);
        System.out.println(ds);
        Connection conn = ds.getConnection();
        System.out.println(conn);
    }


    /**
     *
     *   GoodEmployeeMapper主为了测试表名是否会去掉下划线，是否可以插入非自动增长id，所以只写一个方法测试
     *
     *   测试结果：
     *      在配置文件中加入如下配置，plus会自动将实体类和字段加上下划线当做DB表名和DB字段用
     *      所以如果用下划线，表名和字段就一起用下划线，不要一个用下划线，一个用驼峰
     *      <!-- 在2.3版本以后，dbColumnUnderline 默认值就是true -->
     * 		<property name="dbColumnUnderline" value="true"></property>
     *
     * 	    非自增主键可以插并返回
     */

    @Test
    public void insertOne(){
        //插入单条记录
        Good_Employee goodEmployee  = new Good_Employee();

        goodEmployee.setEmail("njm@qq.com");
        goodEmployee.setLastName("njm");
        goodEmployee.setId(12345);

        Integer insert = goodEmployeeMapper.insert(goodEmployee);

        System.out.println(insert);

        Integer id = goodEmployee.getId();
        System.out.println(id);
    }
}
