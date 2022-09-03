package xyz.lovegu.emos.api.controller.form.update;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author 老顾
 * @title: UpdateStatusForm
 * @projectName emos-api
 * @email: 1437594522@qq.com
 * @date 2022/8/21 21:52
 */
@Data
@Schema(description = "修改用户为离职表单")
public class UpdateStatusForm {

    @NotNull(message = "userId不能为空")
    @Min(value = 1, message = "userId不能小于1")
    @Schema(description = "用户id")
    private Integer userId;
}
