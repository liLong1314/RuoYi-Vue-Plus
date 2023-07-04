package com.ruoyi.devices.controller;

import java.util.List;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import lombok.RequiredArgsConstructor;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.core.validate.QueryGroup;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.devices.domain.vo.MqttAclVo;
import com.ruoyi.devices.domain.bo.MqttAclBo;
import com.ruoyi.devices.service.IMqttAclService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * mqttUser的acl规则
 *
 * @author 李健
 * @date 2023-07-04
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/devices/mqttAcl")
public class MqttAclController extends BaseController {

    private final IMqttAclService iMqttAclService;

    /**
     * 查询mqttUser的acl规则列表
     */
    @SaCheckPermission("devices:mqttAcl:list")
    @GetMapping("/list")
    public TableDataInfo<MqttAclVo> list(MqttAclBo bo, PageQuery pageQuery) {
        return iMqttAclService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出mqttUser的acl规则列表
     */
    @SaCheckPermission("devices:mqttAcl:export")
    @Log(title = "mqttUser的acl规则", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MqttAclBo bo, HttpServletResponse response) {
        List<MqttAclVo> list = iMqttAclService.queryList(bo);
        ExcelUtil.exportExcel(list, "mqttUser的acl规则", MqttAclVo.class, response);
    }

    /**
     * 获取mqttUser的acl规则详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("devices:mqttAcl:query")
    @GetMapping("/{id}")
    public R<MqttAclVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iMqttAclService.queryById(id));
    }

    /**
     * 新增mqttUser的acl规则
     */
    @SaCheckPermission("devices:mqttAcl:add")
    @Log(title = "mqttUser的acl规则", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MqttAclBo bo) {
        return toAjax(iMqttAclService.insertByBo(bo));
    }

    /**
     * 修改mqttUser的acl规则
     */
    @SaCheckPermission("devices:mqttAcl:edit")
    @Log(title = "mqttUser的acl规则", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MqttAclBo bo) {
        return toAjax(iMqttAclService.updateByBo(bo));
    }

    /**
     * 删除mqttUser的acl规则
     *
     * @param ids 主键串
     */
    @SaCheckPermission("devices:mqttAcl:remove")
    @Log(title = "mqttUser的acl规则", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iMqttAclService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
