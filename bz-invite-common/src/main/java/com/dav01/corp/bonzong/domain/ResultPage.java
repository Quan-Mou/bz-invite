package com.dav01.corp.bonzong.domain;


import lombok.Data;

import java.util.List;


/**
 * 分页返回结果
 * Author:权某
 */
@Data
public class ResultPage {
    private Integer total;
    private List data;
}
