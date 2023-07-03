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
import com.ruoyi.device.domain.vo.MqttUserVo;
import com.ruoyi.device.domain.bo.MqttUserBo;
import com.ruoyi.device.service.IMqttUserService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * mqtt客户的连接鉴权，密码为sha256加密
 *
 * @author 李健
 * @date 2023-07-03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/device/mqttUser")
public class MqttUserController extends BaseController {

    private final IMqttUserService iMqttUserService;

    /**
     * 查询mqtt客户的连接鉴权，密码为sha256加密列表
     */
    @SaCheckPermission("device:mqttUser:list")
    @GetMapping("/list")
    public TableDataInfo<MqttUserVo> list(MqttUserBo bo, PageQuery pageQuery) {
        return iMqttUserService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出mqtt客户的连接鉴权，密码为sha256加密列表
     */
    @SaCheckPermission("device:mqttUser:export")
    @Log(title = "mqtt客户的连接鉴权，密码为sha256加密", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MqttUserBo bo, HttpServletResponse response) {
        List<MqttUserVo> list = iMqttUserService.queryList(bo);
        ExcelUtil.exportExcel(list, "mqtt客户的连接鉴权，密码为sha256加密", MqttUserVo.class, response);
    }

    /**
     * 获取mqtt客户的连接鉴权，密码为sha256加密详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("device:mqttUser:query")
    @GetMapping("/{id}")
    public R<MqttUserVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iMqttUserService.queryById(id));
    }

    /**
     * 新增mqtt客户的连接鉴权，密码为sha256加密
     */
    @SaCheckPermission("device:mqttUser:add")
    @Log(title = "mqtt客户的连接鉴权，密码为sha256加密", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MqttUserBo bo) {
        return toAjax(iMqttUserService.insertByBo(bo));
    }

    /**
     * 修改mqtt客户的连接鉴权，密码为sha256加密
     */
    @SaCheckPermission("device:mqttUser:edit")
    @Log(title = "mqtt客户的连接鉴权，密码为sha256加密", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MqttUserBo bo) {
        return toAjax(iMqttUserService.updateByBo(bo));
    }

    /**
     * 删除mqtt客户的连接鉴权，密码为sha256加密
     *
     * @param ids 主键串
     */
    @SaCheckPermission("device:mqttUser:remove")
    @Log(title = "mqtt客户的连接鉴权，密码为sha256加密", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iMqttUserService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
