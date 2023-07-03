package com.ruoyi.devices.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * mqtt客户的连接鉴权，密码为sha256加密视图对象 t_mqtt_user
 *
 * @author 李健
 * @date 2023-07-03
 */
@Data
@ExcelIgnoreUnannotated
public class MqttUserVo {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @ExcelProperty(value = "编号")
    private Long id;

    /**
     * 用户名
     */
    @ExcelProperty(value = "用户名")
    private String username;

    /**
     * 密码
     */
    @ExcelProperty(value = "密码")
    private String password;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
