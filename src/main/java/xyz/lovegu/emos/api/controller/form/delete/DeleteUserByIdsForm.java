package xyz.lovegu.emos.api.controller.form.delete;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author 老顾
 * @title: DeleteUserByIdsForm
 * @projectName emos-api
 * @email: 1437594522@qq.com
 * @date 2022/8/21 17:18
 */
@Data
@Schema(description = "删除用户表单")
public class DeleteUserByIdsForm {

    @NotEmpty(message = "ids不能为空")
    @Schema(description = "用户id")
    private Integer[] ids;
}
