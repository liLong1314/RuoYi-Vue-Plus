package com.ruoyi.device.mapper;

import com.ruoyi.device.domain.MqttUser;
import com.ruoyi.device.domain.vo.MqttUserVo;
import com.ruoyi.common.core.mapper.BaseMapperPlus;

/**
 * mqtt客户的连接鉴权，密码为sha256加密Mapper接口
 *
 * @author 李健
 * @date 2023-07-03
 */
public interface MqttUserMapper extends BaseMapperPlus<MqttUserMapper, MqttUser, MqttUserVo> {

}
