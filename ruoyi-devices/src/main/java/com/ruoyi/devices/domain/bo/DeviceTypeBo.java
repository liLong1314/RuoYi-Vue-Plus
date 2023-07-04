package com.ruoyi.devices.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 设备类型业务对象 t_device_type
 *
 * @author 李健
 * @date 2023-07-04
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class DeviceTypeBo extends BaseEntity {

    /**
     * 新加的
     */
    private Long id;

    /**
     * 设备类型名
     */
    @NotBlank(message = "设备类型名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 型号
     */
    @NotBlank(message = "型号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String deviceModel;

    /**
     * 设备码
     */
    @NotBlank(message = "设备码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String code;

    /**
     * 设备图片
     */
    private String pic;


}
