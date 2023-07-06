package com.ruoyi.test;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.devices.controller.DeviceController;
import com.ruoyi.devices.domain.Device;
import com.ruoyi.devices.domain.MqttUser;
import com.ruoyi.devices.domain.bo.DeviceBo;
import com.ruoyi.devices.mapper.MqttUserMapper;
import com.ruoyi.devices.service.IDeviceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author lijia
 * @createTime 2023/7/5 20:57
 */
@SpringBootTest
public class DevicesTest {

    @Resource
    IDeviceService deviceService;
    @Resource
    MqttUserMapper mqttUserMapper;

    @Test
    public void testDelectMap(){
        List<Long> ids = new ArrayList<>();
        ids.add(1676168083894104065L);
        Boolean aBoolean = deviceService.deleteWithValidByIds(ids, true);

        deviceService.deleteWithValidByIds(ids, true);

    }
//    @Test
//    public void testselcet(){
//        List<Long> ids = new ArrayList<>();
//        ids.add(16L);
//        ids.add(17L);
//        LambdaQueryWrapper<M>
//        List<MqttUser> mqttUsers = mqttUserMapper.selectList(ids);
//        System.out.println(mqttUsers);
////        deviceService.deleteWithValidByIds(ids, true);
//
//    }

}
