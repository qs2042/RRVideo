package com.qing.rrvideo.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qing.common.utils.PageUtils;
import com.qing.rrvideo.member.entity.MemberEntity;

import java.util.Map;

/**
 * 会员
 *
 * @author halfRain
 * @email 2042136767@qq.com
 * @date 2022-12-06 18:38:17
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

