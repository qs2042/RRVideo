package com.qing.rrvideo.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qing.common.utils.PageUtils;
import com.qing.rrvideo.product.entity.SpuInfoEntity;

import java.util.Map;

/**
 * spu信息
 *
 * @author halfRain
 * @email 2042136767@qq.com
 * @date 2022-12-03 17:18:22
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

