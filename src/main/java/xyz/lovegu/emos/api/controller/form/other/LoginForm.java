package xyz.lovegu.emos.api.controller.form.other;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author 老顾
 * @title: LoginForm
 * @projectName emos-api
 * @email: 1437594522@qq.com
 * @date 2022/8/18 17:26
 */
@Data
@Schema(description = "登录表单类")
public class LoginForm {

    @NotBlank(message = "username不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{5,20}$",message = "username内容不正确")
    @Schema(description = "用户名")
    private String username;

    @NotBlank(message = "password不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,20}", message = "passowrd内容不正确")
    @Schema(description = "密码")
    private String password;
}
