package com.mybatisplustest.beans;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * @program: mybatisplus-test
 * @description: 好员工
 * @author: Mr.Ning
 * @create: 2019-01-29 09:27
 **/

@TableName("good_employee")         //因为加上了表名全局配置，所有实体类在映射为表名时会自动加上tbl_,但这个表没有tbl_,所以需要特意指定表名
public class Good_Employee {         //数据库表名为good_employee(测试是否会去掉下划线)

//    @TableId(type = IdType.UUID)
    @TableId(type = IdType.INPUT)       //因为加上了id全局配置，所有字段在映射为表字段时会自动视为自增长的id,但这个表没有是手动插入id,所以需要特意指定idType
    private Integer id ;    //非自增id(测试手动添加id)

    @TableField("lastName")     //因为配置了会为驼峰规则自动添加下划线的配置，所以这个字段需要特指。（BD字段为lastName,但它会映射为last_name）
    private String lastName;

    private String email ;

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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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
        return "com.mybatisplustest.beans.Employee [id=" + id + ", lastName=" + lastName + ", email=" + email
                + ", gender=" + gender + ", age="
                + age + "]";
    }
}