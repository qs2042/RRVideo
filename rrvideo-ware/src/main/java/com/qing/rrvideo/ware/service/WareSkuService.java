package com.qing.rrvideo.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qing.common.utils.PageUtils;
import com.qing.rrvideo.ware.entity.WareSkuEntity;

import java.util.Map;

/**
 * 商品库存
 *
 * @author halfRain
 * @email 2042136767@qq.com
 * @date 2022-12-06 20:00:04
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

