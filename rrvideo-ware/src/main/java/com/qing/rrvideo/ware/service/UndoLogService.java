package com.qing.rrvideo.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qing.common.utils.PageUtils;
import com.qing.rrvideo.ware.entity.UndoLogEntity;

import java.util.Map;

/**
 * 
 *
 * @author halfRain
 * @email 2042136767@qq.com
 * @date 2022-12-06 20:00:04
 */
public interface UndoLogService extends IService<UndoLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

