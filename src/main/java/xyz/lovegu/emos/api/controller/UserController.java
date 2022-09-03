package xyz.lovegu.emos.api.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.json.JSONUtil;
import xyz.lovegu.emos.api.common.util.PageUtils;
import xyz.lovegu.emos.api.common.util.R;
import xyz.lovegu.emos.api.controller.form.delete.DeleteUserByIdsForm;
import xyz.lovegu.emos.api.controller.form.insert.InsertUserForm;
import xyz.lovegu.emos.api.controller.form.other.CheckQrCodeForm;
import xyz.lovegu.emos.api.controller.form.other.LoginForm;
import xyz.lovegu.emos.api.controller.form.other.WechatLoginForm;
import xyz.lovegu.emos.api.controller.form.search.SearchUserByIdForm;
import xyz.lovegu.emos.api.controller.form.search.SearchUserByPageForm;
import xyz.lovegu.emos.api.controller.form.update.UpdatePasswordForm;
import xyz.lovegu.emos.api.controller.form.update.UpdateStatusForm;
import xyz.lovegu.emos.api.controller.form.update.UpdateUserForm;
import xyz.lovegu.emos.api.db.dataobject.TbUserDO;
import xyz.lovegu.emos.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

@RestController
@RequestMapping("/user")
@Tag(name = "UserController", description = "用户Web接口")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 生成登陆二维码的字符串
     */
    @GetMapping("/createQrCode")
    @Operation(summary = "生成二维码Base64格式的字符串")
    public R createQrCode() {
        HashMap map = userService.createQrCode();
        return R.ok(map);
    }

    /**
     * 检测登陆验证码
     *
     * @param form
     * @return
     */
    @PostMapping("/checkQrCode")
    @Operation(summary = "检测登陆验证码")
    public R checkQrCode(@Valid @RequestBody CheckQrCodeForm form) {
        boolean bool = userService.checkQrCode(form.getCode(), form.getUuid());
        return R.ok().put("result", bool);
    }

    @PostMapping("/wechatLogin")
    @Operation(summary = "微信小程序登陆")
    public R wechatLogin(@Valid @RequestBody WechatLoginForm form) {
        HashMap map = userService.wechatLogin(form.getUuid());
        boolean result = (boolean) map.get("result");
        if (result) {
            int userId = (int) map.get("userId");
            StpUtil.setLoginId(userId);
            Set<String> permissions = userService.searchUserPermissions(userId);
            map.remove("userId");
            map.put("permissions", permissions);
        }
        return R.ok(map);
    }

    /**
     * 登陆成功后加载用户的基本信息
     */
    @GetMapping("/loadUserInfo")
    @Operation(summary = "登陆成功后加载用户的基本信息")
    @SaCheckLogin
    public R loadUserInfo() {
        int userId = StpUtil.getLoginIdAsInt();
        HashMap summary = userService.searchUserSummary(userId);
        return R.ok(summary);
    }

    @PostMapping("/searchById")
    @Operation(summary = "根据ID查找用户")
    @SaCheckPermission(value = {"ROOT", "USER:SELECT"}, mode = SaMode.OR)
    public R searchById(@Valid @RequestBody SearchUserByIdForm form) {
        HashMap map = userService.searchById(form.getUserId());
        return R.ok(map);
    }

    @GetMapping("/searchAllUser")
    @Operation(summary = "查询所有用户")
    @SaCheckLogin
    public R searchAllUser() {
        ArrayList<HashMap> list = userService.searchAllUser();
        return R.ok().put("list", list);
    }

    @PostMapping("/login")
    @Operation(summary = "用户登录系统")
    public R login(@Valid @RequestBody LoginForm form){
        HashMap param = JSONUtil.parse(form).toBean(HashMap.class);
        Integer userId = userService.login(param);
        R r = R.ok().put("result", userId !=null ? true : false);
        if (userId !=null){
            //调Sa-token权限验证框架的工具类的方法验证userId
            StpUtil.setLoginId(userId);
            Set<String> permissions = userService.searchUserPermissions(userId);
            r.put("permissions", permissions);
        }
        return r;
    }

    @GetMapping("/logout")
    @Operation(summary = "退出系统")
    public R logout() {
        StpUtil.logout();
        return R.ok();
    }

    @PostMapping("/updatePassword")
    @SaCheckLogin
    @Operation(summary = "修改密码")
    public R updatePassword(@Valid @RequestBody UpdatePasswordForm form) {
        //把Token字符串的UserId转成int类型
        int userId = StpUtil.getLoginIdAsInt();
        //这里的{{ }}就是在创建HashMap的时候可以给对象赋值，是JDK的新语法
        HashMap param = new HashMap(){{
            put("userId", userId);
            put("password", form.getPassword());
        }};
        int rows = userService.updatePassword(param);
        return R.ok().put("rows", rows);
    }

    @GetMapping("/searchUserCount")
    @Operation(summary = "查询用户总数")
    public  R searchUserCount(HashMap param){
        long rows = userService.searchUserCount(param);
        return R.ok().put("rows", rows);
    }
    @PostMapping("/searchUserByPage")
    @Operation(summary = "查询用户分页记录")
    @SaCheckPermission(value = {"ROOT", "USER:SELECT"}, mode = SaMode.OR)
    public R searchUserByPage(@Valid @RequestBody SearchUserByPageForm form) {
        int page = form.getPage();
        int length = form.getLength();
        int start = (page - 1) * length;
        HashMap param = JSONUtil.parse(form).toBean(HashMap.class);
        param.put("start",start);
        PageUtils pageUtils = userService.searchUserByPage(param);
        return R.ok().put("page",pageUtils);
    }

    @PostMapping("/insert")
    @SaCheckPermission(value = {"ROOT", "USER:INSERT"}, mode = SaMode.OR)
    @Operation(summary = "添加用户")
    public R insert(@Valid @RequestBody InsertUserForm form) {
        //把form数据转换成映射类的对象
        TbUserDO user = JSONUtil.parse(form).toBean(TbUserDO.class);
        user.setStatus((byte) 1);
        user.setRole(JSONUtil.parseArray(form.getRole()).toString());
        user.setCreateTime(new Date());
        int rows = userService.insert(user);
        return R.ok().put("rows", rows);
    }

    @PostMapping("/update")
    @SaCheckPermission(value = {"ROOT","USER:UPDATE"}, mode = SaMode.OR)
    @Operation(summary = "修改用户")
    public R update(@Valid @RequestBody UpdateUserForm form) {
        HashMap param = JSONUtil.parse(form).toBean(HashMap.class);
        param.replace("role",JSONUtil.parseArray(form.getRole()).toString());
        int rows = userService.update(param);
        if (rows == 1){
            //修改资料后，把该用户踢下线
            StpUtil.logoutByLoginId(form.getUserId());
        }
        return R.ok().put("rows", rows);
    }

    @PostMapping("/deleteUserByIds")
    @Operation(summary = "批量删除用户")
    @SaCheckPermission(value = {"ROOT","USER:DELETE"}, mode = SaMode.OR)
    public R deleteUserByIds(@Valid @RequestBody DeleteUserByIdsForm form){
        String userId = StpUtil.getLoginIdAsString();
        if (ArrayUtil.contains(form.getIds(), userId)){
            return R.error("您不能删除自己的账户");
        }
        int rows = userService.deleteUserByIds(form.getIds());
        if (rows > 0){
            //把被删除的用户踢下线
            for (Integer id : form.getIds()) {
                StpUtil.logoutByLoginId(id);
            }
        }
        return R.ok().put("rows", rows);
    }

    @PostMapping("/updateStatus")
    @SaCheckPermission(value = {"ROOT","USER:UPDATE"}, mode = SaMode.OR)
    @Operation(summary = "修改用户状态为离职")
    public R updateStatus(@Valid @RequestBody UpdateStatusForm form) {
        HashMap param = JSONUtil.parse(form).toBean(HashMap.class);
        int rows = userService.updateStatus(param);
        return R.ok().put("rows",rows);
    }
}
