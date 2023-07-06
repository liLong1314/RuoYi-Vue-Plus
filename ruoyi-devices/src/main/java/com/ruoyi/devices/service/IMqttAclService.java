package com.ruoyi.devices.service;

import com.ruoyi.devices.domain.MqttAcl;
import com.ruoyi.devices.domain.vo.MqttAclVo;
import com.ruoyi.devices.domain.bo.MqttAclBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * mqttUser的acl规则Service接口
 *
 * @author 李健
 * @date 2023-07-04
 */
public interface IMqttAclService {

    /**
     * 查询mqttUser的acl规则
     */
    MqttAclVo queryById(Long id);

    /**
     * 查询mqttUser的acl规则列表
     */
    TableDataInfo<MqttAclVo> queryPageList(MqttAclBo bo, PageQuery pageQuery);

    /**
     * 查询mqttUser的acl规则列表
     */
    List<MqttAclVo> queryList(MqttAclBo bo);

    /**
     * 新增mqttUser的acl规则
     */
    Boolean insertByBo(MqttAclBo bo);

    /**
     * 修改mqttUser的acl规则
     */
    Boolean updateByBo(MqttAcl bo);

    /**
     * 校验并批量删除mqttUser的acl规则信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
