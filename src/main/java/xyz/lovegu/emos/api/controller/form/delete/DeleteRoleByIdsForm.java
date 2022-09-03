package xyz.lovegu.emos.api.controller.form.delete;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author 老顾
 * @title: DeleteRoleByIdsForm
 * @projectName emos-api
 * @email: 1437594522@qq.com
 * @date 2022/8/26 10:16
 */
@Data
@Schema(description = "批量删除角色表单")
public class DeleteRoleByIdsForm {

    @NotEmpty(message = "ids不能为空")
    @Schema(description = "部门id")
    private Integer[] ids;
}
