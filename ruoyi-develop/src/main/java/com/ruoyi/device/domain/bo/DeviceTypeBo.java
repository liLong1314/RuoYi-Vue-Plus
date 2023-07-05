package com.ruoyi.device.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 功能名：设备类型的管理业务对象 t_device_type
 *
 * @author 李健
 * @date 2023-07-03
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class DeviceTypeBo extends BaseEntity {

    /**
     * 
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
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
    @NotBlank(message = "设备图片不能为空", groups = { AddGroup.class, EditGroup.class })
    private String pic;

    /**
     * json自定义属性
     */
    @NotBlank(message = "json自定义属性不能为空", groups = { AddGroup.class, EditGroup.class })
    private String attributes;

    /**
     * 1:已删除，0:正常
     */
    @NotNull(message = "1:已删除，0:正常不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer deleted;


}
