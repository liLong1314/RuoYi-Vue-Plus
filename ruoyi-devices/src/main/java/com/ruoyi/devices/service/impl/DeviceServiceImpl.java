package com.ruoyi.devices.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.devices.domain.bo.DeviceBo;
import com.ruoyi.devices.domain.vo.DeviceVo;
import com.ruoyi.devices.domain.Device;
import com.ruoyi.devices.mapper.DeviceMapper;
import com.ruoyi.devices.service.IDeviceService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 设备的管理Service业务层处理
 *
 * @author 李健
 * @date 2023-07-03
 */
@RequiredArgsConstructor
@Service
public class DeviceServiceImpl implements IDeviceService {

    private final DeviceMapper baseMapper;

    /**
     * 查询设备的管理
     */
    @Override
    public DeviceVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询设备的管理列表
     */
    @Override
    public TableDataInfo<DeviceVo> queryPageList(DeviceBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Device> lqw = buildQueryWrapper(bo);
        Page<DeviceVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询设备的管理列表
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
        lqw.eq(StringUtils.isNotBlank(bo.getRemark()), Device::getRemark, bo.getRemark());
        lqw.eq(bo.getDeleted() != null, Device::getDeleted, bo.getDeleted());
        return lqw;
    }

    /**
     * 新增设备的管理
     */
    @Override
    public Boolean insertByBo(DeviceBo bo) {
        Device add = BeanUtil.toBean(bo, Device.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改设备的管理
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
    }

    /**
     * 批量删除设备的管理
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
