package com.ruoyi.device.mapper;

import com.ruoyi.device.domain.Device;
import com.ruoyi.device.domain.vo.DeviceVo;
import com.ruoyi.common.core.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备的管理Mapper接口
 *
 * @author 李健
 * @date 2023-07-03
 */
//@Mapper
public interface DeviceMapper extends BaseMapperPlus<DeviceMapper, Device, DeviceVo> {

}
