package xyz.lovegu.emos.api.controller.form.insert;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author 老顾
 * @title: InsertMeetingForm
 * @projectName emos-api
 * @email: 1437594522@qq.com
 * @date 2022/8/30 17:12
 */
@Data
@Schema(description = "添加会议表单")
public class InsertMeetingForm {

    @NotBlank(message = "title不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9\\u4e00-\\u9fa5]{2,30}$", message = "title内容不正确")
    @Schema(description = "主题")
    private String title;

    @NotBlank(message = "date不能为空")
    @Pattern(regexp = "^[1-9]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$",message = "date内容不正确")
    @Schema(description = "日期")
    private String date;

    @Pattern(regexp = "^[a-zA-Z0-9\\u4e00-\\u9fa5]{2,20}$", message = "place内容不正确")
    @Schema(description = "会议地点")
    private String place;

    @NotBlank(message = "start不能为空")
    @Pattern(regexp = "^([01]?[0-9]|2[0-3]):(00|30)$",message = "start内容不正确")
    @Schema(description = "起始时间")
    private String start;

    @NotBlank(message = "end不能为空")
    @Pattern(regexp = "^([01]?[0-9]|2[0-3]):(00|30)$",message = "end内容不正确")
    @Schema(description = "结束时间")
    private String end;

    @NotNull(message = "type不能为空")
    @Range(min = 1, max = 2, message = "type内容不正确")
    @Schema(description = "会议类型")
    private Byte type;

    @NotBlank(message = "members不能为空")
    @Schema(description = "参会人")
    private String members;

    @NotBlank(message = "desc不能为空")
    @Length(min = 1, max = 200)
    @Schema(description = "内容")
    private String desc;
}
