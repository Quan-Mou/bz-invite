package com.dav01.corp.bonzong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dav01.corp.bonzong.dao.mapper.NewsMapper;
import com.dav01.corp.bonzong.domain.entity.News;
import com.dav01.corp.bonzong.service.INewsService;
import org.springframework.stereotype.Service;

/**
 * (TNews)表服务实现类
 *
 * @author RestfulToolkitXCode
 * @since 2024-03-21 19:56:28
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements INewsService {

}

