package com.ruoyi.devices.service;

import com.ruoyi.devices.domain.DeviceType;
import com.ruoyi.devices.domain.vo.DeviceTypeVo;
import com.ruoyi.devices.domain.bo.DeviceTypeBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 功能名：设备类型的管理Service接口
 *
 * @author 李健
 * @date 2023-07-03
 */
public interface IDeviceTypeService {

    /**
     * 查询功能名：设备类型的管理
     */
    DeviceTypeVo queryById(Long id);

    /**
     * 查询功能名：设备类型的管理列表
     */
    TableDataInfo<DeviceTypeVo> queryPageList(DeviceTypeBo bo, PageQuery pageQuery);

    /**
     * 查询功能名：设备类型的管理列表
     */
    List<DeviceTypeVo> queryList(DeviceTypeBo bo);

    /**
     * 新增功能名：设备类型的管理
     */
    Boolean insertByBo(DeviceTypeBo bo);

    /**
     * 修改功能名：设备类型的管理
     */
    Boolean updateByBo(DeviceTypeBo bo);

    /**
     * 校验并批量删除功能名：设备类型的管理信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
