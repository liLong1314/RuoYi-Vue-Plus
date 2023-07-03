package com.ruoyi.device.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * mqtt客户的acl规则，符合该规则的发布/订阅才可行业务对象 t_mqtt_acl
 *
 * @author 李健
 * @date 2023-07-03
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class MqttAclBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String username;

    /**
     * 主题
     */
    @NotBlank(message = "主题不能为空", groups = { AddGroup.class, EditGroup.class })
    private String topic;

    /**
     * 访问类型： 1->订阅；2->发布；3->订阅与发布
     */
    @NotNull(message = "访问类型： 1->订阅；2->发布；3->订阅与发布不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long access;

    /**
     * 是否允许访问：0-deny，1-allow
     */
    @NotNull(message = "是否允许访问：0-deny，1-allow不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long allow;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { AddGroup.class, EditGroup.class })
    private String remark;


}
