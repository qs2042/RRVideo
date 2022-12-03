package com.qing.rrvideo.product.dao;

import com.qing.rrvideo.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author halfRain
 * @email 2042136767@qq.com
 * @date 2022-12-03 17:18:21
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
