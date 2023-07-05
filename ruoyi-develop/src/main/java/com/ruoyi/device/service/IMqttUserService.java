package com.ruoyi.device.service;

import com.ruoyi.device.domain.MqttUser;
import com.ruoyi.device.domain.vo.MqttUserVo;
import com.ruoyi.device.domain.bo.MqttUserBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * mqtt客户的连接鉴权，密码为sha256加密Service接口
 *
 * @author 李健
 * @date 2023-07-03
 */
public interface IMqttUserService {

    /**
     * 查询mqtt客户的连接鉴权，密码为sha256加密
     */
    MqttUserVo queryById(Long id);

    /**
     * 查询mqtt客户的连接鉴权，密码为sha256加密列表
     */
    TableDataInfo<MqttUserVo> queryPageList(MqttUserBo bo, PageQuery pageQuery);

    /**
     * 查询mqtt客户的连接鉴权，密码为sha256加密列表
     */
    List<MqttUserVo> queryList(MqttUserBo bo);

    /**
     * 新增mqtt客户的连接鉴权，密码为sha256加密
     */
    Boolean insertByBo(MqttUserBo bo);

    /**
     * 修改mqtt客户的连接鉴权，密码为sha256加密
     */
    Boolean updateByBo(MqttUserBo bo);

    /**
     * 校验并批量删除mqtt客户的连接鉴权，密码为sha256加密信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
