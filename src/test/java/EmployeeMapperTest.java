import com.mybatisplustest.beans.Employee;
import com.mybatisplustest.mapper.EmployeeMapper;
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

public class EmployeeMapperTest {
    private ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
    private EmployeeMapper employeeMapper = ioc.getBean("employeeMapper",EmployeeMapper.class);

    //测试连接DB
    @Test
    public void testDataSource() throws Exception {
        DataSource ds  = ioc.getBean("dataSource",DataSource.class);
        System.out.println(ds);
        Connection conn = ds.getConnection();
        System.out.println(conn);
    }

    @Test
    public void insertOne(){
        //插入单条记录
        Employee employee  = new Employee();
        employee.setMyEmail("njm2@qq.com");
        employee.setLastName("njm2");

        //insert()方法：当有些字段不设置值时，不会插入该字段（plus会做非空判断）
        //例如：INSERT INTO tbl_employee ( last_name,email ) VALUES ( ?,? )
//        Integer insert = employeeMapper.insert(employee);   //insert为影响条数
//        System.out.println(insert);

        //insertAllColumn()方法：不管是否非空，全部插入字段
        //例如：INSERT INTO tbl_employee ( last_name,email,gender,age ) VALUES ( ?,?,?,? )
        Integer integer = employeeMapper.insertAllColumn(employee);
        System.out.println(integer);


        Integer id = employee.getId();      //plus会自动返回实体类的id，会自动插入传入的实体类内
        System.out.println(id);
    }
}
