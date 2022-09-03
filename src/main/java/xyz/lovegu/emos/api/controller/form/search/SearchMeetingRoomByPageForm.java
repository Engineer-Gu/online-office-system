package xyz.lovegu.emos.api.controller.form.search;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Schema(description = "查询会议室分页表单")
public class SearchMeetingRoomByPageForm {

    @Pattern(regexp = "^[0-9a-zA-Z\\u4e00-\\u9fa5]{1,20}$", message = "name内容不正确")
    @Schema(description = "会议室名称")
    private String name;

    @Schema(description = "是否可以删除")
    private Boolean canDelete;

    @NotNull(message = "page不能为空")
    @Min(value = 1, message = "page不能小于1")
    @Schema(description = "页数")
    private Integer page;

    @NotNull(message = "length不能为空")
    @Range(min = 10, max = 50, message = "length必须在10~50之间")
    @Schema(description = "每页记录数")
    private Integer length;
}
