package com.ruoyi.devices.service;

import com.ruoyi.devices.domain.DeviceType;
import com.ruoyi.devices.domain.vo.DeviceTypeVo;
import com.ruoyi.devices.domain.bo.DeviceTypeBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 设备类型Service接口
 *
 * @author 李健
 * @date 2023-07-04
 */
public interface IDeviceTypeService {

    /**
     * 查询设备类型
     */
    DeviceTypeVo queryById(Long id);

    /**
     * 查询设备类型列表
     */
    TableDataInfo<DeviceTypeVo> queryPageList(DeviceTypeBo bo, PageQuery pageQuery);

    /**
     * 查询设备类型列表
     */
    List<DeviceTypeVo> queryList(DeviceTypeBo bo);

    /**
     * 新增设备类型
     */
    Boolean insertByBo(DeviceTypeBo bo);

    /**
     * 修改设备类型
     */
    Boolean updateByBo(DeviceTypeBo bo);

    /**
     * 校验并批量删除设备类型信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
