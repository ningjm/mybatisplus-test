package com.mybatisplustest.beans;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * @program: mybatisplus-test
 * @description: 员工
 * @author: Mr.Ning
 * @create: 2019-01-29 09:27
 **/
//@TableName("tbl_employee")        //配置文件配置了全局的表前缀策略配置 ,会在实体名前自动加上tbl_,就不用此注解了
public class Employee {         //数据库表名为tbl_employee(带tbl前缀),可以在配置文件中配置加上tbl_前缀,就不用tableName了

    //在配置文件加上<!-- 全局的主键策略 -->，所以可以忽略此注解
//    @TableId(value = "id",type = IdType.AUTO)       //如果BD字段和实体字段一致，可去掉value = "id",重点是type = IdType.AUTO指定该字段为自动增长
    private Integer id ;    //数据库自增id

    private String lastName;    //数据库名为last_name(带下划线),会自动加上下划线

    @TableField("email")
    private String my_email ;      //数据库名为email(与数据库表名不一致)

    private Integer gender ;

    private Integer age ;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getMyEmail() {
        return my_email;
    }
    public void setMyEmail(String my_email) {
        this.my_email = my_email;
    }
    public Integer getGender() {
        return gender;
    }
    public void setGender(Integer gender) {
        this.gender = gender;
    }
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return "com.mybatisplustest.beans.Employee [id=" + id + ", lastName=" + lastName + ", email=" + my_email
                + ", gender=" + gender + ", age="
                + age + "]";
    }
}
