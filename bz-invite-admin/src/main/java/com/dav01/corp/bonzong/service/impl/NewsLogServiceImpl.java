package com.dav01.corp.bonzong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dav01.corp.bonzong.dao.mapper.NewsLogMapper;
import com.dav01.corp.bonzong.domain.entity.NewsLog;
import com.dav01.corp.bonzong.service.INewsLogService;
import org.springframework.stereotype.Service;

/**
 * (NewsLog)表服务实现类
 *
 * @author RestfulToolkitXCode
 * @since 2024-03-21 19:56:28
 */
@Service
public class NewsLogServiceImpl extends ServiceImpl<NewsLogMapper, NewsLog> implements INewsLogService {

}

