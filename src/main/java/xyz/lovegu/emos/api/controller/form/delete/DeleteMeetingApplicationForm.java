package xyz.lovegu.emos.api.controller.form.delete;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author 老顾
 * @title: DeleteMeetingApplicationForm
 * @projectName emos-api
 * @email: 1437594522@qq.com
 * @date 2022/9/2 17:27
 */
@Data
@Schema(description = "删除会议申请表单")
public class DeleteMeetingApplicationForm {

    @NotNull(message = "id不能为空")
    @Min(value = 1)
    @Schema(description = "id")
    private Long id;

    @NotBlank(message = "uuid不能为空")
    @Schema(description = "uuid")
    private String uuid;

    @Schema(description = "工作流instanceId")
    private String instanceId;

    @NotBlank(message = "原因不能为空")
    @Schema(description = "原因")
    private String reason;
}
