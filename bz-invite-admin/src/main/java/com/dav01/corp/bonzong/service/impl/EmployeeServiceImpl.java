package com.dav01.corp.bonzong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dav01.corp.bonzong.dao.mapper.EmployeeMapper;
import com.dav01.corp.bonzong.domain.R;
import com.dav01.corp.bonzong.domain.dto.AddEmployeeDTO;
import com.dav01.corp.bonzong.domain.entity.Employee;
import com.dav01.corp.bonzong.service.IEmployeeService;
import org.springframework.stereotype.Service;

/**
 * (Employee)表服务实现类
 *
 * @author RestfulToolkitXCode
 * @since 2024-03-21 19:56:28
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

    @Override
    public R insert(AddEmployeeDTO employee) {
        return null;
    }

    @Override
    public R update(Employee employee) {
        return null;
    }
}

