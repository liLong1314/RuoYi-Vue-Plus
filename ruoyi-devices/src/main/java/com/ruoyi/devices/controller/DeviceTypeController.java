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
import com.ruoyi.devices.domain.vo.DeviceTypeVo;
import com.ruoyi.devices.domain.bo.DeviceTypeBo;
import com.ruoyi.devices.service.IDeviceTypeService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 设备类型
 *
 * @author 李健
 * @date 2023-07-04
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/devices/deviceType")
@SaIgnore
public class DeviceTypeController extends BaseController {

    private final IDeviceTypeService iDeviceTypeService;

    /**
     * 查询设备类型列表
     */
    @SaCheckPermission("devices:deviceType:list")
    @GetMapping("/list")
    public TableDataInfo<DeviceTypeVo> list(DeviceTypeBo bo, PageQuery pageQuery) {
        return iDeviceTypeService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出设备类型列表
     */
    @SaCheckPermission("devices:deviceType:export")
    @Log(title = "设备类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DeviceTypeBo bo, HttpServletResponse response) {
        List<DeviceTypeVo> list = iDeviceTypeService.queryList(bo);
        ExcelUtil.exportExcel(list, "设备类型", DeviceTypeVo.class, response);
    }

    /**
     * 获取设备类型详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("devices:deviceType:query")
    @GetMapping("/{id}")
    public R<DeviceTypeVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iDeviceTypeService.queryById(id));
    }

    /**
     * 新增设备类型
     */
    @SaCheckPermission("devices:deviceType:add")
    @Log(title = "设备类型", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DeviceTypeBo bo) {
        return toAjax(iDeviceTypeService.insertByBo(bo));
    }

    /**
     * 修改设备类型
     */
    @SaCheckPermission("devices:deviceType:edit")
    @Log(title = "设备类型", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DeviceTypeBo bo) {
        return toAjax(iDeviceTypeService.updateByBo(bo));
    }

    /**
     * 删除设备类型
     *
     * @param ids 主键串
     */
    @SaCheckPermission("devices:deviceType:remove")
    @Log(title = "设备类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iDeviceTypeService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
