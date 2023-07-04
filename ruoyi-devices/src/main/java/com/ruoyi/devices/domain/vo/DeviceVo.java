package com.ruoyi.devices.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * 设备管理视图对象 t_device
 *
 * @author 李健
 * @date 2023-07-04
 */
@Data
@ExcelIgnoreUnannotated
public class DeviceVo {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 设备编号
     */
    @ExcelProperty(value = "设备编号")
    private String serialNum;

    /**
     * 外键, 设备类型id
     */
    @ExcelProperty(value = "外键, 设备类型id")
    private Long deviceTypeId;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;

    /**
     * 1:已删除，0:正常
     */
    @ExcelProperty(value = "1:已删除，0:正常")
    private Integer deleted;


}
