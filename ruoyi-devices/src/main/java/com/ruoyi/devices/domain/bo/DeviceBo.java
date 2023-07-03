package com.ruoyi.devices.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 设备的管理业务对象 t_device
 *
 * @author 李健
 * @date 2023-07-03
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class DeviceBo extends BaseEntity {

    /**
     * 
     */
    private Long id;

    /**
     * 设备编号
     */
    @NotBlank(message = "设备编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String serialNum;

    /**
     * 外键, 设备类型id
     */
    @NotNull(message = "外键, 设备类型id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long deviceTypeId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 1:已删除，0:正常
     */
    @NotNull(message = "1:已删除，0:正常不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer deleted;


}
