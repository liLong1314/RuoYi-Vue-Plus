package com.ruoyi.devices.mapper;

import com.ruoyi.devices.domain.Device;
import com.ruoyi.devices.domain.vo.DeviceVo;
import com.ruoyi.common.core.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 设备管理Mapper接口
 *
 * @author 李健
 * @date 2023-07-04
 */
//@Mapper
public interface DeviceMapper extends BaseMapperPlus<DeviceMapper, Device, DeviceVo> {

}
