package xyz.lovegu.emos.api.controller.form.delete;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author 老顾
 * @title: DeleteMeetingRoomByIdsForm
 * @projectName emos-api
 * @email: 1437594522@qq.com
 * @date 2022/9/2 17:40
 */
@Schema(description = "删除会议室表单")
@Data
public class DeleteMeetingRoomByIdsForm {

    @NotEmpty(message = "ids不能为空")
    @Schema(description = "会议室ID")
    private Integer[] ids;
}
