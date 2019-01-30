package com.mybatisplustest.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mybatisplustest.beans.Employee;

/**
 * @program: mybatisplus-test
 * @description: 接口
 * @author: Mr.Ning
 * @create: 2019-01-29 09:57
 **/


/**
 * 注意：BaseMapper<Good_Employee>要指定具体的类，不能为：BaseMapper<T>
 */
public interface EmployeeMapper extends BaseMapper<Employee> {
    int del(int a); //mybatisX 插件快速开发
}
