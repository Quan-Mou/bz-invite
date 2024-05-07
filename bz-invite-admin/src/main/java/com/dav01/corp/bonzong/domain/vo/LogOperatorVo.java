package com.dav01.corp.bonzong.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: 权某人
 * @Description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogOperatorVo {
    private List<Integer> jobId;
    private boolean status;

    public boolean getStatus() {
        return status;
    }
}
