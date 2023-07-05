package com.ruoyi.devices;

import com.ruoyi.devices.domain.bo.DeviceBo;
import com.ruoyi.devices.mapper.DeviceMapper;
import com.ruoyi.devices.service.IDeviceService;
import com.ruoyi.devices.service.impl.DeviceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Author lijia
 * @createTime 2023/7/5 0:53
 */
@SpringBootApplication
@RunWith(SpringRunner.class)
@DisplayName("DeviceServiceImpl test")
public class ServiceTest {

//
    @Resource
    DeviceMapper deviceMapper;
    @Test
    @DisplayName("Should throw an exception when the device serial number is null")
    public void test1(){
        Long aLong = deviceMapper.selectCount(null);
        System.out.println(aLong);

    }
}
