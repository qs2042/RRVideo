package com.qing.rrvideo.member.feign;

import com.qing.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
// 告诉SpringCloud, 这个接口是一个远程客户端
// @FeignClient(value = "rrvideo-coupon", url = "http://localhost:7000/")
@FeignClient("rrvideo-coupon")
public interface CouponFeignService {
    // requestMapping = 被远程调用的类+方法上面的requestMapping组合起来
    // 方法名字规范最好和被远程调用的类的方法名一样
    @RequestMapping("/coupon/coupon/member/list")
    public R memberCoupons();
}
