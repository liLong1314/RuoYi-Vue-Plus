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
import com.ruoyi.device.domain.vo.DeviceVo;
import com.ruoyi.device.domain.bo.DeviceBo;
import com.ruoyi.device.service.IDeviceService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 设备的管理
 *
 * @author 李健
 * @date 2023-07-03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/device/device")
public class DeviceController extends BaseController {

    private final IDeviceService iDeviceService;

    /**
     * 查询设备的管理列表
     */
    @SaCheckPermission("device:device:list")
    @GetMapping("/list")
    public TableDataInfo<DeviceVo> list(DeviceBo bo, PageQuery pageQuery) {
        return iDeviceService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出设备的管理列表
     */
    @SaCheckPermission("device:device:export")
    @Log(title = "设备的管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DeviceBo bo, HttpServletResponse response) {
        List<DeviceVo> list = iDeviceService.queryList(bo);
        ExcelUtil.exportExcel(list, "设备的管理", DeviceVo.class, response);
    }

    /**
     * 获取设备的管理详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("device:device:query")
    @GetMapping("/{id}")
    public R<DeviceVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iDeviceService.queryById(id));
    }

    /**
     * 新增设备的管理
     */
    @SaCheckPermission("device:device:add")
    @Log(title = "设备的管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DeviceBo bo) {
        return toAjax(iDeviceService.insertByBo(bo));
    }

    /**
     * 修改设备的管理
     */
    @SaCheckPermission("device:device:edit")
    @Log(title = "设备的管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DeviceBo bo) {
        return toAjax(iDeviceService.updateByBo(bo));
    }

    /**
     * 删除设备的管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("device:device:remove")
    @Log(title = "设备的管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iDeviceService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
