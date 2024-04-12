package com.dav01.corp.bonzong.controller;

import com.dav01.corp.bonzong.domain.R;
import com.dav01.corp.bonzong.domain.dto.AddEmployeeDTO;
import com.dav01.corp.bonzong.domain.entity.Employee;
import com.dav01.corp.bonzong.service.IEmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: 权某人
 * @create: 2024-03-25 23:10
 * @Description:
 */

@Api(value = "员工管理")
@RestController
@RequestMapping("/employee")
public class EmployeeManagerController {

    @Autowired
    private IEmployeeService employeeService;




    @ApiOperation(value = "创建员工")
    @PostMapping("save")
    public R save(@RequestBody AddEmployeeDTO employee) {
//        TODO:创建员工
        return employeeService.insert(employee);
    }



    @ApiOperation(value = "修改员工")
    @PostMapping("update")
    public R update(@RequestBody Employee employee) {
//        TODO:修改员工状态
        return employeeService.update(employee);
    }






}
