package com.qing.rrvideo.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qing.common.utils.PageUtils;
import com.qing.rrvideo.product.entity.SpuImagesEntity;

import java.util.Map;

/**
 * spu图片
 *
 * @author halfRain
 * @email 2042136767@qq.com
 * @date 2022-12-03 17:18:22
 */
public interface SpuImagesService extends IService<SpuImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

