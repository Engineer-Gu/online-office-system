package xyz.lovegu.emos.api.controller.form.update;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author 老顾
 * @title: UpdatePasswordForm
 * @projectName emos-api
 * @email: 1437594522@qq.com
 * @date 2022/8/19 15:29
 */
@Data
@Schema(description = "修改密码表单")
public class UpdatePasswordForm {

    @NotBlank(message = "password不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,20}", message = "passowrd内容不正确")
    @Schema(description ="密码")
    String password;
}
