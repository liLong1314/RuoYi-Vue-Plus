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
import com.ruoyi.device.domain.vo.DeviceTypeVo;
import com.ruoyi.device.domain.bo.DeviceTypeBo;
import com.ruoyi.device.service.IDeviceTypeService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 功能名：设备类型的管理
 *
 * @author 李健
 * @date 2023-07-03
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/device/deviceType")
public class DeviceTypeController extends BaseController {

    private final IDeviceTypeService iDeviceTypeService;

    /**
     * 查询功能名：设备类型的管理列表
     */
    @SaCheckPermission("device:deviceType:list")
    @GetMapping("/list")
    public TableDataInfo<DeviceTypeVo> list(DeviceTypeBo bo, PageQuery pageQuery) {
        return iDeviceTypeService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出功能名：设备类型的管理列表
     */
    @SaCheckPermission("device:deviceType:export")
    @Log(title = "功能名：设备类型的管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(DeviceTypeBo bo, HttpServletResponse response) {
        List<DeviceTypeVo> list = iDeviceTypeService.queryList(bo);
        ExcelUtil.exportExcel(list, "功能名：设备类型的管理", DeviceTypeVo.class, response);
    }

    /**
     * 获取功能名：设备类型的管理详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("device:deviceType:query")
    @GetMapping("/{id}")
    public R<DeviceTypeVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iDeviceTypeService.queryById(id));
    }

    /**
     * 新增功能名：设备类型的管理
     */
    @SaCheckPermission("device:deviceType:add")
    @Log(title = "功能名：设备类型的管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DeviceTypeBo bo) {
        return toAjax(iDeviceTypeService.insertByBo(bo));
    }

    /**
     * 修改功能名：设备类型的管理
     */
    @SaCheckPermission("device:deviceType:edit")
    @Log(title = "功能名：设备类型的管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DeviceTypeBo bo) {
        return toAjax(iDeviceTypeService.updateByBo(bo));
    }

    /**
     * 删除功能名：设备类型的管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("device:deviceType:remove")
    @Log(title = "功能名：设备类型的管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iDeviceTypeService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
