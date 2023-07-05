package com.ruoyi.device.service;

import com.ruoyi.device.domain.MqttAcl;
import com.ruoyi.device.domain.vo.MqttAclVo;
import com.ruoyi.device.domain.bo.MqttAclBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * mqtt客户的acl规则，符合该规则的发布/订阅才可行Service接口
 *
 * @author 李健
 * @date 2023-07-03
 */
public interface IMqttAclService {

    /**
     * 查询mqtt客户的acl规则，符合该规则的发布/订阅才可行
     */
    MqttAclVo queryById(Long id);

    /**
     * 查询mqtt客户的acl规则，符合该规则的发布/订阅才可行列表
     */
    TableDataInfo<MqttAclVo> queryPageList(MqttAclBo bo, PageQuery pageQuery);

    /**
     * 查询mqtt客户的acl规则，符合该规则的发布/订阅才可行列表
     */
    List<MqttAclVo> queryList(MqttAclBo bo);

    /**
     * 新增mqtt客户的acl规则，符合该规则的发布/订阅才可行
     */
    Boolean insertByBo(MqttAclBo bo);

    /**
     * 修改mqtt客户的acl规则，符合该规则的发布/订阅才可行
     */
    Boolean updateByBo(MqttAclBo bo);

    /**
     * 校验并批量删除mqtt客户的acl规则，符合该规则的发布/订阅才可行信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
