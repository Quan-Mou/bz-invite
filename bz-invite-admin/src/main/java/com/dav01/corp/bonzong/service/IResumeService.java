package com.dav01.corp.bonzong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dav01.corp.bonzong.domain.R;
import com.dav01.corp.bonzong.domain.entity.Resume;
import com.dav01.corp.bonzong.domain.vo.ResumeVo;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * (TResume)表服务接口
 *
 * @author RestfulToolkitXCode
 * @since 2024-03-21 19:56:28
 */
public interface IResumeService extends IService<Resume> {

    List<ResumeVo> list(Integer page,
                        Integer pageSize,
                        Integer jobId,
                        String schoolProvince,
                        Date createTime,
                        Integer isRead,
                        String userName);


}

