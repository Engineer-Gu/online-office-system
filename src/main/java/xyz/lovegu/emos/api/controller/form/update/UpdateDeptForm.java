package xyz.lovegu.emos.api.controller.form.update;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;

/**
 * @author 老顾
 * @title: UpdateDeptForm
 * @projectName emos-api
 * @email: 1437594522@qq.com
 * @date 2022/8/23 15:30
 */
@Data
@Schema(description = "修改部门表单")
public class UpdateDeptForm {

    @NotNull(message = "deptId不能为空")
    @Min(value = 1, message = "deptId不能小于1")
    @Schema(description = "部门id")
    private Integer deptId;

    @NotBlank(message = "deptName不能为空")
    @Schema(description = "部门名称")
    private String deptName;

    @NotBlank(message = "电话不能为空")
    @Pattern(regexp = "^1\\d{10}$", message = "电话格式不正确")
    @Schema(description = "电话")
    private String tel;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "email格式不正确")
    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "备注")
    private String desc;
}
