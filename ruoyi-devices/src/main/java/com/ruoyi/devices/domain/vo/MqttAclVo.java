package com.ruoyi.devices.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import lombok.Data;
import java.util.Date;



/**
 * mqtt客户的acl规则，符合该规则的发布/订阅才可行视图对象 t_mqtt_acl
 *
 * @author 李健
 * @date 2023-07-03
 */
@Data
@ExcelIgnoreUnannotated
public class MqttAclVo {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long id;

    /**
     * 用户名
     */
    @ExcelProperty(value = "用户名")
    private String username;

    /**
     * 主题
     */
    @ExcelProperty(value = "主题")
    private String topic;

    /**
     * 访问类型： 1->订阅；2->发布；3->订阅与发布
     */
    @ExcelProperty(value = "访问类型： 1->订阅；2->发布；3->订阅与发布")
    private Long access;

    /**
     * 是否允许访问：0-deny，1-allow
     */
    @ExcelProperty(value = "是否允许访问：0-deny，1-allow")
    private Long allow;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
