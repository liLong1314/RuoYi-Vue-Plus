package com.ruoyi.devices.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.system.service.ISysOssService;
import com.ruoyi.system.service.impl.SysOssServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.devices.domain.bo.DeviceTypeBo;
import com.ruoyi.devices.domain.vo.DeviceTypeVo;
import com.ruoyi.devices.domain.DeviceType;
import com.ruoyi.devices.mapper.DeviceTypeMapper;
import com.ruoyi.devices.service.IDeviceTypeService;

import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * 设备类型Service业务层处理
 *
 * @author 李健
 * @date 2023-07-04
 */
@RequiredArgsConstructor
@Service
public class DeviceTypeServiceImpl implements IDeviceTypeService {

    private final DeviceTypeMapper baseMapper;
    private  final SysOssServiceImpl service;

    /**
     * 查询设备类型
     */
    @Override
    public DeviceTypeVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询设备类型列表
     */
    @Override
    public TableDataInfo<DeviceTypeVo> queryPageList(DeviceTypeBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<DeviceType> lqw = buildQueryWrapper(bo);
        Page<DeviceTypeVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        for (DeviceTypeVo deviceTypeVo : result.getRecords()) {
            deviceTypeVo.setPic(service.selectUrlByIds(deviceTypeVo.getPic()));
        }
        return TableDataInfo.build(result);
    }

    /**
     * 查询设备类型列表
     */
    @Override
    public List<DeviceTypeVo> queryList(DeviceTypeBo bo) {
        LambdaQueryWrapper<DeviceType> lqw = buildQueryWrapper(bo);
        List<DeviceTypeVo> deviceTypeVos = baseMapper.selectVoList(lqw).stream().map(item ->{
                DeviceTypeVo deviceTypeVo = new DeviceTypeVo();
                BeanUtil.copyProperties(item,deviceTypeVo);
                String pic = service.selectUrlByIds(item.getPic());
                deviceTypeVo.setPic(pic);
                return deviceTypeVo;
            }
        ).collect(Collectors.toList());

        return deviceTypeVos;
    }

    private LambdaQueryWrapper<DeviceType> buildQueryWrapper(DeviceTypeBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<DeviceType> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getName()), DeviceType::getName, bo.getName());
        lqw.like(StringUtils.isNotBlank(bo.getDeviceModel()), DeviceType::getDeviceModel, bo.getDeviceModel());
        return lqw;
    }

    /**
     * 新增设备类型
     */
    @Override
    public Boolean insertByBo(DeviceTypeBo bo) {
        DeviceType add = BeanUtil.toBean(bo, DeviceType.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());

        }
        return flag;
    }

    /**
     * 修改设备类型
     */
    @Override
    public Boolean updateByBo(DeviceTypeBo bo) {
        DeviceType update = BeanUtil.toBean(bo, DeviceType.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(DeviceType entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除设备类型
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
