package com.qing.rrvideo.member.controller;

import java.util.Arrays;
import java.util.Map;

import com.qing.rrvideo.member.feign.CouponFeignService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qing.rrvideo.member.entity.MemberEntity;
import com.qing.rrvideo.member.service.MemberService;
import com.qing.common.utils.PageUtils;
import com.qing.common.utils.R;



/**
 * 会员
 *
 * @author halfRain
 * @email 2042136767@qq.com
 * @date 2022-12-06 18:38:17
 */
@RestController
@RequestMapping("member/member")
public class MemberController {
    @Autowired
    private MemberService memberService;


    // 测试远程调用
    @Autowired
    CouponFeignService couponFeignService;

    @RequestMapping("/coupons")
    public R test() {
        MemberEntity entity = new MemberEntity();
        entity.setNickname("小晴");
        R r = couponFeignService.memberCoupons();

        return R.ok().put("member", entity).put("coupons", r.get("coupons"));
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("member:member:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = memberService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("member:member:info")
    public R info(@PathVariable("id") Long id){
		MemberEntity member = memberService.getById(id);

        return R.ok().put("member", member);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("member:member:save")
    public R save(@RequestBody MemberEntity member){
		memberService.save(member);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("member:member:update")
    public R update(@RequestBody MemberEntity member){
		memberService.updateById(member);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("member:member:delete")
    public R delete(@RequestBody Long[] ids){
		memberService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
