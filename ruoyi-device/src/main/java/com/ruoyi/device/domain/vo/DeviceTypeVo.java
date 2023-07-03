package com.ruoyi.device.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 功能名：设备类型的管理视图对象 t_device_type
 *
 * @author 李健
 * @date 2023-07-03
 */
@Data
@ExcelIgnoreUnannotated
public class DeviceTypeVo {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 设备类型名
     */
    @ExcelProperty(value = "设备类型名")
    private String name;

    /**
     * 型号
     */
    @ExcelProperty(value = "型号")
    private String deviceModel;

    /**
     * 设备码
     */
    @ExcelProperty(value = "设备码")
    private String code;

    /**
     * 设备图片
     */
    @ExcelProperty(value = "设备图片")
    private String pic;

    /**
     * json自定义属性
     */
    @ExcelProperty(value = "json自定义属性")
    private String attributes;

    /**
     * 1:已删除，0:正常
     */
    @ExcelProperty(value = "1:已删除，0:正常")
    private Integer deleted;


}
