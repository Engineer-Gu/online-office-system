package xyz.lovegu.emos.api.controller.form.delete;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author 老顾
 * @title: DeleteDeptByIdsForm
 * @projectName emos-api
 * @email: 1437594522@qq.com
 * @date 2022/8/23 15:47
 */
@Data
@Schema(description = "批量删除部门表单")
public class DeleteDeptByIdsForm {

    @NotEmpty(message = "ids不能为空")
    @Schema(description = "部门id")
    private Integer[] ids;
}
