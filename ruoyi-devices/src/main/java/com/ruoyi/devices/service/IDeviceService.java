package com.ruoyi.devices.service;

import com.ruoyi.devices.domain.Device;
import com.ruoyi.devices.domain.vo.DeviceVo;
import com.ruoyi.devices.domain.bo.DeviceBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 设备管理Service接口
 *
 * @author 李健
 * @date 2023-07-04
 */
public interface IDeviceService {

    /**
     * 查询设备管理
     */
    DeviceVo queryById(Long id);

    /**
     * 查询设备管理列表
     */
    TableDataInfo<DeviceVo> queryPageList(DeviceBo bo, PageQuery pageQuery);

    /**
     * 查询设备管理列表
     */
    List<DeviceVo> queryList(DeviceBo bo);

    /**
     * 新增设备管理
     */
    Boolean insertByBo(DeviceBo bo);

    /**
     * 修改设备管理
     */
    Boolean updateByBo(DeviceBo bo);

    /**
     * 校验并批量删除设备管理信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
