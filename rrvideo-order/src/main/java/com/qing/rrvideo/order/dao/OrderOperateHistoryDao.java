package com.qing.rrvideo.order.dao;

import com.qing.rrvideo.order.entity.OrderOperateHistoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单操作历史记录
 * 
 * @author halfRain
 * @email 2042136767@qq.com
 * @date 2022-12-06 19:04:06
 */
@Mapper
public interface OrderOperateHistoryDao extends BaseMapper<OrderOperateHistoryEntity> {
	
}
