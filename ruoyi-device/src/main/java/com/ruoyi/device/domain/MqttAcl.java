package com.ruoyi.device.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * mqtt客户的acl规则，符合该规则的发布/订阅才可行对象 t_mqtt_acl
 *
 * @author 李健
 * @date 2023-07-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_mqtt_acl")
public class MqttAcl extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 主题
     */
    private String topic;
    /**
     * 访问类型： 1->订阅；2->发布；3->订阅与发布
     */
    private Long access;
    /**
     * 是否允许访问：0-deny，1-allow
     */
    private Long allow;
    /**
     * 备注
     */
    private String remark;

}
