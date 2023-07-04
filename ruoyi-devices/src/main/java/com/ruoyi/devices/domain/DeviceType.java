package com.ruoyi.devices.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 设备类型对象 t_device_type
 *
 * @author 李健
 * @date 2023-07-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_device_type")
public class DeviceType extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 设备类型名
     */
    private String name;
    /**
     * 型号
     */
    private String deviceModel;
    /**
     * 设备码
     */
    private String code;
    /**
     * 设备图片
     */
    private String pic;
    /**
     * json自定义属性
     */
    private String attributes;
    /**
     * 1:已删除，0:正常
     */
    private Integer deleted;

}
