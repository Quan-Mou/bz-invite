package com.dav01.corp.bonzong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dav01.corp.bonzong.domain.R;
import com.dav01.corp.bonzong.domain.dto.AddEmployeeDTO;
import com.dav01.corp.bonzong.domain.entity.Employee;

/**
 * (Employee)表服务接口
 *
 * @author RestfulToolkitXCode
 * @since 2024-03-21 19:56:28
 */
public interface IEmployeeService extends IService<Employee> {

    /**
     * 新增员工
     * @param employee
     * @return
     */
    R insert(AddEmployeeDTO employee);

    /**
     * 修改员工信息
     * @param employee
     * @return
     */
    R update(Employee employee);
}

