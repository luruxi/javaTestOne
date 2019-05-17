package enn.testone.dao;

import enn.testone.entity.Employee;
import tk.mybatis.mapper.common.Mapper;

public interface EmployeeMapper extends Mapper<Employee> {
    Employee getOneEmpById(Integer id);
}