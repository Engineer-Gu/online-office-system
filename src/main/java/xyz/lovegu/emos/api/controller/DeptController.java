package xyz.lovegu.emos.api.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import cn.hutool.json.JSONUtil;
import xyz.lovegu.emos.api.common.util.R;
import xyz.lovegu.emos.api.controller.form.delete.DeleteDeptByIdsForm;
import xyz.lovegu.emos.api.controller.form.insert.InsertDeptForm;
import xyz.lovegu.emos.api.controller.form.search.SearchDeptByIdForm;
import xyz.lovegu.emos.api.controller.form.search.SearchDeptPageForm;
import xyz.lovegu.emos.api.controller.form.update.UpdateDeptForm;
import xyz.lovegu.emos.api.db.dataobject.TbDeptDO;
import xyz.lovegu.emos.api.service.DeptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/dept")
@Tag(name = "DeptController", description = "部门Web接口")
@RequiredArgsConstructor
public class DeptController {

    private final DeptService deptService;

    @GetMapping("/searchAllDept")
    @Operation(summary = "查询所有部门")
    public R searchAllDept() {
        ArrayList<HashMap> list = deptService.searchAllDept();
        return R.ok().put("list", list);
    }

    @PostMapping("/searchById")
    @Operation(summary = "根据ID查询部门")
    @SaCheckPermission(value = {"ROOT", "DEPT:SELECT"}, mode = SaMode.OR)
    public R searchById(@Valid @RequestBody SearchDeptByIdForm form) {
        HashMap map = deptService.searchById(form.getId());
        return R.ok(map);
    }
    
    @PostMapping("/searchDeptList")
    @Operation(summary = "查询部门列表数据")
    @SaCheckPermission(value = {"ROOT", "DEPT:SELECT"}, mode = SaMode.OR)
    public R searchDeptList(@Valid @RequestBody SearchDeptPageForm form) {
        HashMap map = JSONUtil.parse(form).toBean(HashMap.class);
        return R.ok().put("map", deptService.searchDeptList(map));
    }

    @PostMapping("/insertDept")
    @Operation(summary = "插入部门数据")
    @SaCheckPermission(value = {"ROOT", "USER:INSERT"}, mode = SaMode.OR)
    public R insertDept(@Valid @RequestBody InsertDeptForm form) {
        TbDeptDO dept = JSONUtil.parse(form).toBean(TbDeptDO.class);
        int rows = deptService.insertDept(dept);
        return R.ok().put("rows", rows);
    }

    @PostMapping("/updateDept")
    @SaCheckPermission(value = {"ROOT","USER:UPDATE"}, mode = SaMode.OR)
    @Operation(summary = "修改部门数据")
    public R updateDept(@Valid @RequestBody UpdateDeptForm form) {
        HashMap param = JSONUtil.parse(form).toBean(HashMap.class);
        int rows = deptService.updateDept(param);
        return R.ok().put("rows", rows);
    }

    @PostMapping("/deleteDeptByIds")
    @SaCheckPermission(value = {"ROOT","USER:DELETE"}, mode = SaMode.OR)
    @Operation(summary = "批量删除部门数据")
    public R deleteDeptByIds(@Valid @RequestBody DeleteDeptByIdsForm form) {
        int rows = deptService.deleteDeptByIds(form.getIds());
        return R.ok().put("rows", rows);
    }
}