package xyz.lovegu.emos.api.controller.form.search;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author 老顾
 * @title: SearchRoleByListForm
 * @projectName emos-api
 * @email: 1437594522@qq.com
 * @date 2022/8/25 16:52
 */
@Data
@Schema(description = "查询角色列表表单")
public class SearchRoleByListForm {

    @Schema(description = "角色id")
    private Integer roleId;

    @Schema(description = "角色名称")
    private String roleName;

    @Schema(description = "权限数量")
    private String permissions;

    @Schema(description = "备注")
    private String desc;

    @Schema(description = "内置角色")
    private Boolean systemic;
}
