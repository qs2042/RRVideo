package com.qing.rrvideo.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qing.common.utils.PageUtils;
import com.qing.rrvideo.coupon.entity.SpuBoundsEntity;

import java.util.Map;

/**
 * 商品spu积分设置
 *
 * @author halfRain
 * @email 2042136767@qq.com
 * @date 2022-12-06 18:18:04
 */
public interface SpuBoundsService extends IService<SpuBoundsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

