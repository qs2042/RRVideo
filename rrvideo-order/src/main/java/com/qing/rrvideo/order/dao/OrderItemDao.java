package com.qing.rrvideo.order.dao;

import com.qing.rrvideo.order.entity.OrderItemEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单项信息
 * 
 * @author halfRain
 * @email 2042136767@qq.com
 * @date 2022-12-06 19:04:06
 */
@Mapper
public interface OrderItemDao extends BaseMapper<OrderItemEntity> {
	
}
