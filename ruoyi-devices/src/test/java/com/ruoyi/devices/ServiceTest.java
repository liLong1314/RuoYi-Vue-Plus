package com.ruoyi.devices;

import com.ruoyi.devices.domain.bo.DeviceBo;
import com.ruoyi.devices.service.IDeviceService;
import com.ruoyi.devices.service.impl.DeviceServiceImpl;
import org.junit.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

/**
 * @Author lijia
 * @createTime 2023/7/5 0:53
 */
@SpringBootApplication
public class ServiceTest {

    @Resource
    IDeviceService deviceService;
    @Test
    public void test(){
        DeviceBo deviceBo = new DeviceBo();
        deviceBo.setSerialNum("12138");
        deviceBo.setDeviceTypeId(1L);
        deviceBo.setRemark("哈哈哈");
        Boolean aBoolean = deviceService.insertByBo(deviceBo);
        System.out.println(aBoolean);
    }
}
