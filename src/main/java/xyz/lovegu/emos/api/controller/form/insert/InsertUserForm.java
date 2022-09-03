package xyz.lovegu.emos.api.controller.form.insert;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;

/**
 * @author 老顾
 * @title: InsertUserForm
 * @projectName emos-api
 * @email: 1437594522@qq.com
 * @date 2022/8/21 10:15
 */
@Data
@Schema(description = "添加用户表单")
public class InsertUserForm {

    @NotBlank(message = "username不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{5,20}$",message = "username内容不正确")
    @Schema(description = "用户名")
    private String username;

    @NotBlank(message = "password不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,20}", message = "passowrd内容不正确")
    @Schema(description = "密码")
    private String password;

    @NotBlank(message = "name不能为空")
    @Pattern(regexp = "^[\\u4e00-\\u9fa5]{1,10}$", message = "name内容不正确")
    @Schema(description = "姓名")
    private String name;

    @NotBlank(message = "sex不能为空")
    @Pattern(regexp = "^男$|^女$", message = "sex内容不正确")
    @Schema(description = "性别")
    private String sex;

    @NotBlank(message = "del不能为空")
    @Pattern(regexp = "^1\\d{10}$", message = "tel内容不正确")
    @Schema(description = "电话")
    private String tel;

    @NotBlank(message = "email内容不正确")
    @Email(message = "email格式不正确")
    @Schema(description = "邮箱")
    private String email;

    @NotBlank(message = "hiredate不能为空")
    @Pattern(regexp = "^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$")
    @Schema(description = "入职日期")
    private String hiredate;

    @NotEmpty(message = "role不能为空")
    @Schema(description = "角色")
    private Integer[] role;

    @NotNull(message = "deptId不能为空")
    @Min(value = 1, message = "deptId不能小于1")
    @Schema(description = "部门")
    private Integer deptId;
}
