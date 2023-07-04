package com.ruoyi.devices.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 设备类型视图对象 t_device_type
 *
 * @author 李健
 * @date 2023-07-04
 */
@Data
@ExcelIgnoreUnannotated
public class DeviceTypeVo {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
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


}
