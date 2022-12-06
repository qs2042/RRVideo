package com.qing.rrvideo.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qing.common.utils.PageUtils;
import com.qing.rrvideo.order.entity.RefundInfoEntity;

import java.util.Map;

/**
 * 退款信息
 *
 * @author halfRain
 * @email 2042136767@qq.com
 * @date 2022-12-06 19:04:06
 */
public interface RefundInfoService extends IService<RefundInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

