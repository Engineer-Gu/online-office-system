package xyz.lovegu.emos.api.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import xyz.lovegu.emos.api.common.util.R;
import xyz.lovegu.emos.api.db.dataobject.TbAmectTypeDO;
import xyz.lovegu.emos.api.service.AmectTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/amect_type")
@Tag(name = "AmectTypeController", description = "罚款类型Web接口")
@RequiredArgsConstructor
public class AmectTypeController {

    private final AmectTypeService amectTypeService;

    @GetMapping("/searchAllAmectType")
    @Operation(summary = "查询所有罚款类型")
    @SaCheckLogin
    public R searchAllAmectType() {
        ArrayList<TbAmectTypeDO> list = amectTypeService.searchAllAmectType();
        return R.ok().put("list", list);
    }
}
