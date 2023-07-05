package com.ruoyi.device.mapper;

import com.ruoyi.device.domain.MqttAcl;
import com.ruoyi.device.domain.vo.MqttAclVo;
import com.ruoyi.common.core.mapper.BaseMapperPlus;

/**
 * mqtt客户的acl规则，符合该规则的发布/订阅才可行Mapper接口
 *
 * @author 李健
 * @date 2023-07-03
 */
public interface MqttAclMapper extends BaseMapperPlus<MqttAclMapper, MqttAcl, MqttAclVo> {

}
