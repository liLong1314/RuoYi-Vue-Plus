package com.ruoyi.devices.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.devices.domain.MqttUser;
import com.ruoyi.devices.domain.bo.MqttAclBo;
import com.ruoyi.devices.domain.bo.MqttUserBo;
import com.ruoyi.devices.mapper.MqttUserMapper;
import com.ruoyi.devices.service.IMqttAclService;
import com.ruoyi.devices.service.IMqttUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.devices.domain.bo.DeviceBo;
import com.ruoyi.devices.domain.vo.DeviceVo;
import com.ruoyi.devices.domain.Device;
import com.ruoyi.devices.mapper.DeviceMapper;
import com.ruoyi.devices.service.IDeviceService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 设备管理Service业务层处理
 *
 * @author 李健
 * @date 2023-07-04
 */
@RequiredArgsConstructor
@Service
@Transactional
public class DeviceServiceImpl implements IDeviceService {


//    @Resource
      private final DeviceMapper deviceMapper;
//    @Resource
      private final IMqttUserService iMqttUserService;
      private final MqttUserMapper mqttUserMapper;

    /**
     * 查询设备管理
     */
    @Override
    public DeviceVo queryById(Long id){
        return deviceMapper.selectVoById(id);
    }

    /**
     * 查询设备管理列表
     */
    @Override
    public TableDataInfo<DeviceVo> queryPageList(DeviceBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Device> lqw = buildQueryWrapper(bo);
        Page<DeviceVo> result = deviceMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询设备管理列表
     */
    @Override
    public List<DeviceVo> queryList(DeviceBo bo) {
        LambdaQueryWrapper<Device> lqw = buildQueryWrapper(bo);
        return deviceMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Device> buildQueryWrapper(DeviceBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Device> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getSerialNum()), Device::getSerialNum, bo.getSerialNum());
        lqw.eq(bo.getDeviceTypeId() != null, Device::getDeviceTypeId, bo.getDeviceTypeId());
        lqw.eq(bo.getDeleted() != null, Device::getDeleted, bo.getDeleted());
        return lqw;
    }

    /**
     * 新增设备管理
     */
    @Override
    public Boolean insertByBo(DeviceBo bo) {
        //转化成device
        Device add = BeanUtil.toBean(bo, Device.class);
        validEntityBeforeSave(add);
//        插入device
        boolean flag = deviceMapper.insert(add) > 0;
        //擦入MqttUser
        MqttUserBo mqttUserBo = new MqttUserBo();
        mqttUserBo.setUsername(add.getSerialNum());
        Boolean flag1 = iMqttUserService.insertByBo(mqttUserBo);

        if (flag) {
            bo.setId(add.getId());
        }
        return flag && flag1;
    }

    /**
     * 修改设备管理
     */
    @Override
    public Boolean updateByBo(DeviceBo bo) {
        Device update = BeanUtil.toBean(bo, Device.class);
        validEntityBeforeSave(update);
//        修改MqttUser的Username
        LambdaQueryWrapper<MqttUser> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(MqttUser::getUsername, bo.getSerialNum());
//        調用Db中的查詢查出來mqttUserBo
        MqttUser mqttUser = Db.getOne(lambdaQueryWrapper);

//        調用iMqttUserService中的更新進行同步更新
        Boolean flag = iMqttUserService.updateByBo(mqttUser);
        return deviceMapper.updateById(update) > 0 && flag;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Device entity){
        //TODO 做一些数据校验,如唯一约束
        if (entity.getDeviceTypeId() == null) {
            throw new IllegalArgumentException("设备类型是必须的！！！");
        } else if (entity.getSerialNum() == null) {
            throw new IllegalArgumentException("设备的SerialNum是必须的");
        }
        String serialNum = entity.getSerialNum();
        LambdaQueryWrapper<Device> lambdaQueryWrapper =new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Device::getSerialNum, serialNum);
        Long count = deviceMapper.selectCount(lambdaQueryWrapper);
        if (count > 0 ) {
            throw new IllegalArgumentException("设备类型不唯一，请重新检查！！！");
        }
//        Wrappers.lambdaQuery()

    }

    /**
     * 批量删除设备管理
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){

            List<DeviceVo> deviceVos = deviceMapper.selectVoBatchIds(ids);
//            for ()

            for (DeviceVo deviceVo : deviceVos ){
                //                舍弃这种方式太麻烦了，效率不行
                LambdaQueryWrapper<MqttUser> lambdaQueryWrapper =new LambdaQueryWrapper<MqttUser>();
                lambdaQueryWrapper.eq(MqttUser::getUsername, deviceVo.getSerialNum());
//                mqttUserMapper.deleteByMap()
                List<MqttUser> mqttUsers = mqttUserMapper.selectList(lambdaQueryWrapper);

                List<Long> iids = new ArrayList<Long>();
                for (MqttUser mqttUser:mqttUsers){
                    iids.add(mqttUser.getId());
//                    mqttUserMapper.deleteById(mqttUser.getId());
                }

                System.out.println(iids);
                iMqttUserService.deleteWithValidByIds(iids, isValid);

////                更喜欢这种方式，更简单
//                Map<String, Object> map = new HashMap<>();
//                map.put("username", deviceVo.getSerialNum());
//                System.out.println(mqttUserMapper.deleteByMap(map) > 0);

            }
//            for ()
//            iMqttUserService.
        }
        boolean flag = deviceMapper.deleteBatchIds(ids) > 0;

        return flag ;
    }
}
