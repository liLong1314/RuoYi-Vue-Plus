package com.ruoyi.devices.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * mqtt客户的连接鉴权，密码为sha256加密对象 t_mqtt_user
 *
 * @author 李健
 * @date 2023-07-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_mqtt_user")
public class MqttUser extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 编号
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 备注
     */
    private String remark;

}
