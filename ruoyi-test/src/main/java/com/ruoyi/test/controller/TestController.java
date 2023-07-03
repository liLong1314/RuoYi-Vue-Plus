package com.ruoyi.test.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import com.ruoyi.common.core.domain.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lijia
 * @createTime 2023/7/3 0:58
 */
//    @SaIgnore
@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("/te")
    public R<Void> test (){
        return R.ok();

    }
}
