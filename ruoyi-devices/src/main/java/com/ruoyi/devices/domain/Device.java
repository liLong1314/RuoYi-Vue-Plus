package com.ruoyi.devices.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 设备管理对象 t_device
 *
 * @author 李健
 * @date 2023-07-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_device")
public class Device extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     *
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 设备编号
     */
    private String serialNum;
    /**
     * 外键, 设备类型id
     */
    private Long deviceTypeId;
    /**
     * 备注
     */
    private String remark;
    /**
     * 1:已删除，0:正常
     */
    private Integer deleted;

}
