package xyz.lovegu.emos.api.controller.form.insert;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author 老顾
 * @title: InsertDeptForm
 * @projectName emos-api
 * @email: 1437594522@qq.com
 * @date 2022/8/23 14:45
 */
@Data
@Schema(description = "插入部门表单")
public class InsertDeptForm {

    @NotBlank(message = "deptName不能为空")
    @Schema(description = "部门名称")
    private String deptName;

    @NotBlank(message = "电话不能为空")
    @Pattern(regexp = "^1\\d{10}$", message = "tel内容不正确")
    @Schema(description = "电话")
    private String tel;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "email格式不正确")
    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "备注")
    private String desc;
}
