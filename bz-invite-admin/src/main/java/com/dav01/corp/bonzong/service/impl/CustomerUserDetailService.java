package com.dav01.corp.bonzong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dav01.corp.bonzong.constant.SystemEnum;
import com.dav01.corp.bonzong.dao.mapper.EmployeeMapper;
import com.dav01.corp.bonzong.domain.CustomerUserDetails;
import com.dav01.corp.bonzong.domain.entity.Employee;
import com.dav01.corp.bonzong.handler.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Random;


/**
 * @author: 权某人
 * @create: 2024-03-24 00:12
 * @Description:
 */

@Slf4j
@Service
public class CustomerUserDetailService implements UserDetailsService {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("loadUserByUsername ： 用户名： {}",username);
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
//       使用工号或者手机号登录





//        queryWrapper.eq(Employee::getAccountNumber,username).or().eq(Employee::getPassword,username);
        queryWrapper.eq(Employee::getAccountNumber,username).or().eq(Employee::getPhone,username);

        Employee employee = employeeMapper.selectOne(queryWrapper);
        if (employee == null) {
//            TODO: 全局异常处理只会处理Controller层的异常，为什么service层抛出自定义异常会被Exception捕获
            throw new SystemException(SystemEnum.NOT_EMPLOYEE);
        }



        return new CustomerUserDetails(employee);
    }
}
