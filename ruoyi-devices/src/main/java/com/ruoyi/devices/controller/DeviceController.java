package com.ruoyi.devices.controller;

import java.util.List;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import cn.dev33.satoken.annotation.SaIgnore;
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
import com.ruoyi.devices.domain.vo.DeviceVo;
import com.ruoyi.devices.domain.bo.DeviceBo;
import com.ruoyi.devices.service.IDeviceService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 设备管理
 *
 * @author 李健
 * @date 2023-07-04
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/devices/device")
//@SaIgnore
public class DeviceController extends BaseController {
    /**
     * @RequiredArgsConstructor注解，实现了构造注入。
     * 声明为类的final字段，可以确保在对象创建后不能更改依赖项。这可以提高代码的稳定性和可靠性。
     */
    private final IDeviceService iDeviceService;

    /**
     * 查询设备管理列表
     */
    @SaCheckPermission("devices:device:list")
    @GetMapping("/list")
    public TableDataInfo<DeviceVo> list(DeviceBo bo, PageQuery pageQuery) {
        return iDeviceService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出设备管理列表
     */
    @SaCheckPermission("devices:device:export")
    @Log(title = "设备管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DeviceBo bo, HttpServletResponse response) {
        List<DeviceVo> list = iDeviceService.queryList(bo);
        ExcelUtil.exportExcel(list, "设备管理", DeviceVo.class, response);
    }

    /**
     * 获取设备管理详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("devices:device:query")
    @GetMapping("/{id}")
    public R<DeviceVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iDeviceService.queryById(id));
    }

    /**
     * 新增设备管理
     */
    @SaCheckPermission("devices:device:add")
    @Log(title = "设备管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DeviceBo bo) {

        return toAjax(iDeviceService.insertByBo(bo));
    }

    /**
     * 修改设备管理
     */
    @SaCheckPermission("devices:device:edit")
    @Log(title = "设备管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DeviceBo bo) {
        return toAjax(iDeviceService.updateByBo(bo));
    }

    /**
     * 删除设备管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("devices:device:remove")
    @Log(title = "设备管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iDeviceService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
