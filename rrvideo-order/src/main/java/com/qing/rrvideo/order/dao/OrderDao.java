package com.qing.rrvideo.order.dao;

import com.qing.rrvideo.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author halfRain
 * @email 2042136767@qq.com
 * @date 2022-12-06 19:04:06
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
