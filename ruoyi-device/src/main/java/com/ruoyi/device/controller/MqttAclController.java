package com.ruoyi.device.controller;

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
import com.ruoyi.device.domain.vo.MqttAclVo;
import com.ruoyi.device.domain.bo.MqttAclBo;
import com.ruoyi.device.service.IMqttAclService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * mqtt客户的acl规则，符合该规则的发布/订阅才可行
 *
 * @author 李健
 * @date 2023-07-03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/device/mqttAcl")
public class MqttAclController extends BaseController {

    private final IMqttAclService iMqttAclService;

    /**
     * 查询mqtt客户的acl规则，符合该规则的发布/订阅才可行列表
     */
    @SaCheckPermission("device:mqttAcl:list")
    @GetMapping("/list")
    public TableDataInfo<MqttAclVo> list(MqttAclBo bo, PageQuery pageQuery) {
        return iMqttAclService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出mqtt客户的acl规则，符合该规则的发布/订阅才可行列表
     */
    @SaCheckPermission("device:mqttAcl:export")
    @Log(title = "mqtt客户的acl规则，符合该规则的发布/订阅才可行", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MqttAclBo bo, HttpServletResponse response) {
        List<MqttAclVo> list = iMqttAclService.queryList(bo);
        ExcelUtil.exportExcel(list, "mqtt客户的acl规则，符合该规则的发布/订阅才可行", MqttAclVo.class, response);
    }

    /**
     * 获取mqtt客户的acl规则，符合该规则的发布/订阅才可行详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("device:mqttAcl:query")
    @GetMapping("/{id}")
    public R<MqttAclVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iMqttAclService.queryById(id));
    }

    /**
     * 新增mqtt客户的acl规则，符合该规则的发布/订阅才可行
     */
    @SaCheckPermission("device:mqttAcl:add")
    @Log(title = "mqtt客户的acl规则，符合该规则的发布/订阅才可行", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MqttAclBo bo) {
        return toAjax(iMqttAclService.insertByBo(bo));
    }

    /**
     * 修改mqtt客户的acl规则，符合该规则的发布/订阅才可行
     */
    @SaCheckPermission("device:mqttAcl:edit")
    @Log(title = "mqtt客户的acl规则，符合该规则的发布/订阅才可行", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MqttAclBo bo) {
        return toAjax(iMqttAclService.updateByBo(bo));
    }

    /**
     * 删除mqtt客户的acl规则，符合该规则的发布/订阅才可行
     *
     * @param ids 主键串
     */
    @SaCheckPermission("device:mqttAcl:remove")
    @Log(title = "mqtt客户的acl规则，符合该规则的发布/订阅才可行", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iMqttAclService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
