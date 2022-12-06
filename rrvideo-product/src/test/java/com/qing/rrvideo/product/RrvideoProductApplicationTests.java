package com.qing.rrvideo.product;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.QueryChainWrapper;
import com.qing.rrvideo.product.entity.BrandEntity;
import com.qing.rrvideo.product.service.BrandService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RrvideoProductApplicationTests {
    @Autowired
    BrandService brandService;

    @Test
    void contextLoads() {
        BrandEntity entity = new BrandEntity();
        entity.setName("华为");
        entity.setDescript("中国的一家公司");

        // System.out.println(brandService.save(entity));

        System.out.println(brandService.list());
        System.out.println(brandService.list(new QueryWrapper<BrandEntity>().eq("brand_id", 1)));
        System.out.println(brandService.list(new QueryWrapper<BrandEntity>().eq("brand_id", 2)));

        // brandService.updateById(entity);
    }

}
