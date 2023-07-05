package com.ruoyi.device;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @Author lijia
 * @createTime 2023/7/3 0:35
 */
@SpringBootApplication
//@ComponentScan("com.ruoyi.device")
public class DeviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeviceApplication.class, args);
    }

}
