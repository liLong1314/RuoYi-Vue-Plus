package com.ruoyi.devices.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.devices.domain.bo.MqttAclBo;
import com.ruoyi.devices.domain.bo.MqttUserBo;
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
import java.util.List;
import java.util.Map;
import java.util.Collection;

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
      private final DeviceMapper baseMapper;
//    @Resource
      private final IMqttUserService iMqttUserService;

    /**
     * 查询设备管理
     */
    @Override
    public DeviceVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询设备管理列表
     */
    @Override
    public TableDataInfo<DeviceVo> queryPageList(DeviceBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Device> lqw = buildQueryWrapper(bo);
        Page<DeviceVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询设备管理列表
     */
    @Override
    public List<DeviceVo> queryList(DeviceBo bo) {
        LambdaQueryWrapper<Device> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
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
//        插入devices
        boolean flag = baseMapper.insert(add) > 0;
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
        return baseMapper.updateById(update) > 0;
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
        Long count = baseMapper.selectCount(lambdaQueryWrapper);
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
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
