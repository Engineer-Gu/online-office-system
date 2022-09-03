package xyz.lovegu.emos.api.controller.form.insert;

import cn.hutool.json.JSON;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @author 老顾
 * @title: InsertRoleForm
 * @projectName emos-api
 * @email: 1437594522@qq.com
 * @date 2022/8/26 8:54
 */
@Data
@Schema(description = "插入角色表单")
public class InsertRoleForm {

    @NotBlank(message = "roleName不能为空")
    @Schema(description = "角色名")
    private String roleName;

    @NotEmpty(message = "permissions不能为空")
    @Schema(description = "权限集合")
    private Integer[] permissions;

    @Schema(description = "备注")
    private String desc;
}
