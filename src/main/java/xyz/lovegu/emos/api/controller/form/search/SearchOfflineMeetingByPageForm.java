package xyz.lovegu.emos.api.controller.form.search;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author 老顾
 * @title: SearchOfflineMeetingByPageForm
 * @projectName emos-api
 * @email: 1437594522@qq.com
 * @date 2022/8/27 16:11
 */
@Data
@Schema(description = "查询在线会议分页表单")
public class SearchOfflineMeetingByPageForm {

    @NotNull(message = "page不能为空")
    @Min(value = 1, message = "page不能小于1")
    @Schema(description = "页数")
    private Integer page;

    @NotNull(message = "length不能为空")
    @Range(min = 10, max = 50, message = "length必须在10~50之间")
    @Schema(description = "每页记录数")
    private Integer length;

    private Integer userId;

    private String name;

    private String mold;

    private String date;

}
