package com.dav01.corp.bonzong.controller;

import com.dav01.corp.bonzong.annotation.LogOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 权某人
 * @create: 2024-04-12 18:50
 * @Description:
 */

@RestController
@RequestMapping("demo")
public class DemoController {
//    http://www.jiangxi.gov.cn/module/download/downfile.jsp?classid=0&showname=%E6%B1%9F%E8%A5%BF%E7%9C%81%E6%95%99%E8%82%B2%E5%8E%85%E6%94%BF%E5%8A%A1%E4%BF%A1%E6%81%AF%E5%85%AC%E5%BC%80%E7%94%B3%E8%AF%B7%E8%A1%A8.doc&filename=95f15305d9444bada2993a8b8f4229dd.doc


    @LogOperation(value = "insert")
    @GetMapping("insert")
    public String insert() {
        return "run insert methods";
    }


    @GetMapping("delete")
    public String delete() {
        return "run delete methods";
    }

    @LogOperation(value = "insert")
    @GetMapping("update")
    public String update() {
        return "run update methods";
    }


    @GetMapping("list")
    public String list() {
        return "run list methods";
    }


}
