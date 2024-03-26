package com.dav01.corp.bonzong.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
;import com.dav01.corp.bonzong.domain.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * (Employee)表数据库访问层
 *
 * @author RestfulToolkitXCode
 * @since 2024-03-21 19:56:24
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

}

