package com.ruoyi.device.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.device.domain.bo.DeviceTypeBo;
import com.ruoyi.device.domain.vo.DeviceTypeVo;
import com.ruoyi.device.domain.DeviceType;
import com.ruoyi.device.mapper.DeviceTypeMapper;
import com.ruoyi.device.service.IDeviceTypeService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 功能名：设备类型的管理Service业务层处理
 *
 * @author 李健
 * @date 2023-07-03
 */
@RequiredArgsConstructor
@Service
public class DeviceTypeServiceImpl implements IDeviceTypeService {

    private final DeviceTypeMapper baseMapper;

    /**
     * 查询功能名：设备类型的管理
     */
    @Override
    public DeviceTypeVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询功能名：设备类型的管理列表
     */
    @Override
    public TableDataInfo<DeviceTypeVo> queryPageList(DeviceTypeBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<DeviceType> lqw = buildQueryWrapper(bo);
        Page<DeviceTypeVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询功能名：设备类型的管理列表
     */
    @Override
    public List<DeviceTypeVo> queryList(DeviceTypeBo bo) {
        LambdaQueryWrapper<DeviceType> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<DeviceType> buildQueryWrapper(DeviceTypeBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<DeviceType> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getName()), DeviceType::getName, bo.getName());
        lqw.eq(StringUtils.isNotBlank(bo.getDeviceModel()), DeviceType::getDeviceModel, bo.getDeviceModel());
        lqw.eq(StringUtils.isNotBlank(bo.getCode()), DeviceType::getCode, bo.getCode());
        lqw.eq(StringUtils.isNotBlank(bo.getPic()), DeviceType::getPic, bo.getPic());
        lqw.eq(StringUtils.isNotBlank(bo.getAttributes()), DeviceType::getAttributes, bo.getAttributes());
        lqw.eq(bo.getDeleted() != null, DeviceType::getDeleted, bo.getDeleted());
        return lqw;
    }

    /**
     * 新增功能名：设备类型的管理
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
     * 修改功能名：设备类型的管理
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
     * 批量删除功能名：设备类型的管理
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
