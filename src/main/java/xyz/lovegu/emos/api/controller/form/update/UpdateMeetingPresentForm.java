package xyz.lovegu.emos.api.controller.form.update;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author 老顾
 * @title: UpdateMeetingPresentForm
 * @projectName emos-api
 * @email: 1437594522@qq.com
 * @date 2022/9/3 15:20
 */
@Data
@Schema(description = "会议签到表单")
public class UpdateMeetingPresentForm {

    @NotNull(message = "meetingId不能为空")
    @Min(value = 1, message = "meetingId不能小于1")
    private Integer meetingId;

}
