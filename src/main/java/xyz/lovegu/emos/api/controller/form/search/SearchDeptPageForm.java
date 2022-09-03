package xyz.lovegu.emos.api.controller.form.search;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author 老顾
 * @title: SearchDeptList
 * @projectName emos-api
 * @email: 1437594522@qq.com
 * @date 2022/8/22 16:18
 */
@Data
@Schema(description = "查询部门列表表单")
public class SearchDeptPageForm {

    @Schema(description = "部门ID")
    private Integer deptId;

    @Schema(description = "部门名")
    private String deptName;

    @Schema(description = "电话")
    private String tel;

    @Schema(description = "邮件")
    private String email;

    @Schema(description = "备注")
    private String desc;
}
