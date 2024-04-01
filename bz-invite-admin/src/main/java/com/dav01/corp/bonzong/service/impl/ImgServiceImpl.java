package com.dav01.corp.bonzong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dav01.corp.bonzong.dao.mapper.ImgMapper;
import com.dav01.corp.bonzong.domain.entity.Img;
import com.dav01.corp.bonzong.service.IImgService;
import org.springframework.stereotype.Service;

/**
 * (Img)表服务实现类
 *
 * @author RestfulToolkitXCode
 * @since 2024-03-21 19:56:28
 */
@Service("tImgService")
public class ImgServiceImpl extends ServiceImpl<ImgMapper, Img> implements IImgService {

}

