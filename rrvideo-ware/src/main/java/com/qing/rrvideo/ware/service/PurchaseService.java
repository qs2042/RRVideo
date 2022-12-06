package com.qing.rrvideo.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qing.common.utils.PageUtils;
import com.qing.rrvideo.ware.entity.PurchaseEntity;

import java.util.Map;

/**
 * 采购信息
 *
 * @author halfRain
 * @email 2042136767@qq.com
 * @date 2022-12-06 20:00:04
 */
public interface PurchaseService extends IService<PurchaseEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

