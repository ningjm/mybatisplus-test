import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.mybatisplustest.beans.Employee;
import com.mybatisplustest.mapper.EmployeeMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void insert(){
        //插入单条记录
        Employee employee  = new Employee();
        employee.setMy_Email("njm2@qq.com");
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


    @Test
    public void  update(){
        Employee employee  = new Employee();
        employee.setId(9);
        employee.setMy_Email("njm3@qq.com");
        employee.setLastName("njm3");
        Integer integer = employeeMapper.updateById(employee);      //会进行非空判断，没有赋值的字段会排除掉
        System.out.println(integer);

        //更新所有字段（不要使用）
//        Integer integer1 = employeeMapper.updateAllColumnById(employee);//不要使用该方法。该方法会把你所有的列更新。比如传入的employee.lastName没有赋值。那它会把你数据库更新为NULL
    }

    @Test
    public void  select(){

        //根据id查询，返回单个对象
//        Employee employee = employeeMapper.selectById(7);
//        System.out.println(employee);

        //根据多个条件查询，返回单个对象
//        Employee employee  = new Employee();
//        employee.setMy_Email("njm3@qq.com");
//        employee.setLastName("njm3");       //会自动映射为last_name
////        employee.setAge(1);       //注意：如果为int类型，就算不设置值，它也会默认生成一个条件age=0，因为它有默认值0。所以记得用Integer。
//        employee = employeeMapper.selectOne(employee);
//        System.out.println(employee);



        //根据多个id查询，返回对象集合       ==>SELECT id,last_name AS lastName,email AS my_email,gender,age FROM tbl_employee WHERE id IN ( ? , ? )
//        List<Integer> ids = new ArrayList<Integer>();
//        ids.add(1);
//        ids.add(2);
//        List<Employee> employees = employeeMapper.selectBatchIds(ids);
//        System.out.println(employees);


        //根据Map查询，返回对象集合
//        Map<String,Object> columnMap = new HashMap<String, Object>();
//        columnMap.put("last_name", "njm2"); //注意：此处是表的列名，不是对象属性名lastName
//        columnMap.put("email", "njm2@qq.com ");
//        List<Employee> employees = employeeMapper.selectByMap(columnMap);
//        System.out.println(employees);


        //分页查询，注意：此方法是内存分页（从DB查询符合条件的全部数据下来，再进行内存分页，而非limit物理分页）
        //  ===>SELECT id,last_name AS lastName,email AS my_email,gender,age FROM tbl_employee      没有limit
        //不要使用此方法，使用mybatis的pageHelper，或者mybatis-plus的另一个分页插件
//        List<Employee> employees = employeeMapper.selectPage<Employee>(new Page(2, 2), null);
//        System.out.println(employees);

    }


    @Test
    public void del(){
        //根据id删除
//        Integer integer = employeeMapper.deleteById(7);
//        System.out.println(integer);


        //根据多个id删除
        //===>DELETE FROM tbl_employee WHERE id IN ( ? , ? )
//        List<Integer> ids = new ArrayList<Integer>();
//        ids.add(1);
//        ids.add(2);
//        Integer integer = employeeMapper.deleteBatchIds(ids);
//        System.out.println(integer);


        //根据Map删除
        Map<String,Object> columnMap = new HashMap<String, Object>();
        columnMap.put("last_name", "njm3"); //注意：此处是表的列名，不是对象属性名lastName
        columnMap.put("email", "njm3@qq.com ");
        Integer integer = employeeMapper.deleteByMap(columnMap);
        System.out.println(integer);

    }


    /**
     * 条件构造器EntityWrapper
     */
    @Test
    public void entityWrapper(){

        //分页查询
        //===>SELECT id,last_name AS lastName,email AS my_email,gender,age FROM tbl_employee WHERE (id BETWEEN ? AND ? AND last_name = ?)
//        List<Employee> employees = employeeMapper.selectPage(new Page<Employee>(1, 2),
//                new EntityWrapper<Employee>()
//                        .between("id", 5, 8)        //注意：构造器用的是BD表字段名，不是bean字段
//                        .eq("last_name", "njm2")
//                        .like("email","qq" )
//
//        );
//        System.out.println(employees);


        //使用构造器返回集合==>selectList
        List<Employee> employees = employeeMapper.selectList(new EntityWrapper<Employee>()
                        .eq("last_name", "njm")
                        .or()   //or和orNew区别：使用or：SELECT id,last_name AS lastName,email AS my_email,gender,age FROM tbl_employee WHERE (last_name = ? OR last_name = ?)
//                        .orNew() //SELECT id,last_name AS lastName,email AS my_email,gender,age FROM tbl_employee WHERE (last_name = ?) OR (last_name = ?)
                        .eq("last_name", "njm2")
        );

        System.out.println(employees);
    }

}
