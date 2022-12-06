package com.qing.rrvideo.coupon.dao;

import com.qing.rrvideo.coupon.entity.CouponHistoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券领取历史记录
 * 
 * @author halfRain
 * @email 2042136767@qq.com
 * @date 2022-12-06 18:18:04
 */
@Mapper
public interface CouponHistoryDao extends BaseMapper<CouponHistoryEntity> {
	
}
