package xyz.lovegu.emos.api.controller.form.search;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author 老顾
 * @title: SearchRoomIdByUUIDForm
 * @projectName emos-api
 * @email: 1437594522@qq.com
 * @date 2022/9/3 15:19
 */
@Data
@Schema(description = "查询在线会议室房间ID")
public class SearchRoomIdByUUIDForm {

    @NotBlank(message = "uuid不能为空")
    @Schema(description = "uuid")
    private String uuid;
}
