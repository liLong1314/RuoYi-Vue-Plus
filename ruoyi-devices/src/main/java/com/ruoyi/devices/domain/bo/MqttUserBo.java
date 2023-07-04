package com.ruoyi.devices.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * mqtt客户的连接鉴权，密码为sha256加密业务对象 t_mqtt_user
 *
 * @author 李健
 * @date 2023-07-04
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class MqttUserBo extends BaseEntity {

    /**
     * id
     */
//    @NotBlank(message = "用户名不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long id;
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String password;

    /**
     * 备注
     */
    private String remark;


}
