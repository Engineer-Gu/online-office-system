package xyz.lovegu.emos.api.controller.form.update;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author 老顾
 * @title: UpdateRoleForm
 * @projectName emos-api
 * @email: 1437594522@qq.com
 * @date 2022/8/26 9:58
 */
@Data
@Schema(description = "修改角色表单")
public class UpdateRoleForm {

    @NotNull(message = "roleId不能为空")
    @Schema(description = "角色id")
    private Integer roleId;

    @NotBlank(message = "roleName不能为空")
    @Schema(description = "角色名")
    private String roleName;

    @NotEmpty(message = "permissions不能为空")
    @Schema(description = "权限集合")
    private Integer[] permissions;

    @Schema(description = "备注")
    private String desc;

    @Schema(description = "系统角色内置权限")
    private Integer[] defaultPermissions;

    @Schema(description = "是否为系统内置角色，默认0")
    private Boolean systemic;
}
