package xyz.lovegu.emos.api.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import cn.hutool.json.JSONUtil;
import xyz.lovegu.emos.api.common.util.R;
import xyz.lovegu.emos.api.controller.form.delete.DeleteRoleByIdsForm;
import xyz.lovegu.emos.api.controller.form.insert.InsertRoleForm;
import xyz.lovegu.emos.api.controller.form.search.SearchRoleByIdForm;
import xyz.lovegu.emos.api.controller.form.search.SearchRoleByListForm;
import xyz.lovegu.emos.api.controller.form.update.UpdateRoleForm;
import xyz.lovegu.emos.api.db.dataobject.TbRoleDO;
import xyz.lovegu.emos.api.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/role")
@Tag(name = "RoleController", description = "角色Web接口")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping("/searchAllRole")
    @Operation(summary = "查询所有角色")
    public R searchAllRole() {
        ArrayList<HashMap> list = roleService.searchAllRole();
        return R.ok().put("list", list);
    }

    @PostMapping("/searchById")
    @Operation(summary = "根据ID查询角色")
    @SaCheckPermission(value = {"ROOT", "ROLE:SELECT"}, mode = SaMode.OR)
    public R searchById(@Valid @RequestBody SearchRoleByIdForm form) {
        HashMap map = roleService.searchById(form.getId());
        return R.ok(map);
    }

    @PostMapping("/searchRoleByList")
    @Operation(summary = "查询角色列表")
    @SaCheckPermission(value = {"ROOT", "ROLE:SELECT"}, mode = SaMode.OR)
    public R searchRoleByList(@Valid @RequestBody SearchRoleByListForm form) {
        HashMap map = JSONUtil.parse(form).toBean(HashMap.class);
        return R.ok().put("map", roleService.searchRoleByList(map));
    }

    @PostMapping("/insertRole")
    @Operation(summary = "插入角色")
    @SaCheckPermission(value = {"ROOT", "USER:INSERT"}, mode = SaMode.OR)
    public R insertRole(@Valid @RequestBody InsertRoleForm from) {
        TbRoleDO role = JSONUtil.parse(from).toBean(TbRoleDO.class);
        role.setPermissions(JSONUtil.parseArray(from.getPermissions()).toString());
        role.setSystemic(true);
        int rows = roleService.insertRole(role);
        return R.ok().put("rows", rows);
    }

    @PostMapping("/updateRole")
    @SaCheckPermission(value = {"ROOT","USER:UPDATE"}, mode = SaMode.OR)
    @Operation(summary = "修改角色")
    public R updateRole(@Valid @RequestBody UpdateRoleForm form) {
        HashMap map = JSONUtil.parse(form).toBean(HashMap.class);
        map.replace("permissions", JSONUtil.parseArray(form.getPermissions()).toString());
        map.replace("defaultPermissions",JSONUtil.parseArray(form.getDefaultPermissions()).toString());
        int rows = roleService.updateRole(map);
        return R.ok().put("rows", rows);
    }

    @PostMapping("/deleteRoleByIds")
    @Operation(summary = "批量删除角色")
    @SaCheckPermission(value = {"ROOT","USER:DELETE"}, mode = SaMode.OR)
    public R deleteRoleByIds(@Valid @RequestBody DeleteRoleByIdsForm form) {
        int rows = roleService.deleteRoleByIds(form.getIds());
        return R.ok().put("rows", rows);
    }
}
