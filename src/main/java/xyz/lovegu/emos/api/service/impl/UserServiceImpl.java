package xyz.lovegu.emos.api.service.impl;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import xyz.lovegu.emos.api.common.util.PageUtils;
import xyz.lovegu.emos.api.db.dao.TbUserDao;
import xyz.lovegu.emos.api.db.dataobject.TbUserDO;
import xyz.lovegu.emos.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Value("${wx.app-id}")
    private String appId;

    @Value("${wx.app-secret}")
    private String appSecret;

//    @Value("${workflow.url}")
//    private String workflow;

//    @Value("${emos.code}")
//    private String code;

    private final RedisTemplate redisTemplate;

    private final TbUserDao userDao;

    @Override
    public HashMap createQrCode() {
        //生成一个uuid
        String uuid = IdUtil.simpleUUID();
        //把它存到redis里，uuid是key，value为false，设置过期时间，设置时间单位为分钟
        redisTemplate.opsForValue().set(uuid, false, 5, TimeUnit.MINUTES);
        QrConfig config = new QrConfig();
        //设置二维码的大小
        config.setHeight(160);
        config.setWidth(160);
        config.setMargin(1);
        //拼接生成base64格式的字符串
        String base64 = QrCodeUtil.generateAsBase64("login@@@" + uuid, config, ImgUtil.IMAGE_TYPE_JPG);
        HashMap map = new HashMap() {{
            //把uuid和base64放在hashmap里
            put("uuid", uuid);
            put("pic", base64);
        }};
        return map;
    }

    @Override
    public boolean checkQrCode(String code, String uuid) {
        //从Redis找uuid存到bool里
        boolean bool = redisTemplate.hasKey(uuid);
        //如果uuid存在
        if (bool) {
            //把code生成一个openId
            String openId = getOpenId(code);
            long userId = userDao.searchIdByOpenId(openId);
            redisTemplate.opsForValue().set(uuid, userId);
        }
        return bool;
    }

    @Override
    public HashMap wechatLogin(String uuid) {
        HashMap map = new HashMap();
        boolean result = false;
        //如果Redis的uuid存在
        if (redisTemplate.hasKey(uuid)) {
            //把uuid获取生成一个value
            String value = redisTemplate.opsForValue().get(uuid).toString();
            if (!"false".equals(value)) {
                result = true;
                redisTemplate.delete(uuid);
                int userId = Integer.parseInt(value);
                map.put("userId", userId);
            }
        }
        map.put("result", result);
        return map;
    }

    @Override
    public Set<String> searchUserPermissions(int userId) {
        Set<String> permissions = userDao.searchUserPermissions(userId);
        return permissions;
    }

    @Override
    public HashMap searchById(int userId) {
        HashMap map = userDao.searchById(userId);
        return map;
    }

    @Override
    public HashMap searchUserSummary(int userId) {
        HashMap map = userDao.searchUserSummary(userId);
        return map;
    }

    @Override
    public ArrayList<HashMap> searchAllUser() {
        ArrayList<HashMap> list = userDao.searchAllUser();
        return list;
    }

    @Override
    public Integer login(HashMap param) {
        Integer userId = userDao.login(param);
        return userId;
    }

    @Override
    public int updatePassword(HashMap param) {
        int rows = userDao.updatePassword(param);
        return rows;
    }

    @Override
    public PageUtils searchUserByPage(HashMap param) {
        ArrayList<HashMap> list = userDao.searchUserByPage(param);
        long count = userDao.searchUserCount(param);
        int start = (Integer) param.get("start");
        int length = (Integer) param.get("length");
        //new对象调用PageUtils的构造器传入参数
        return new PageUtils(list, count, start, length);
    }

    @Override
    public long searchUserCount(HashMap param) {
        long rows = userDao.searchUserCount(param);
        return rows;
    }

    @Override
    public int insert(TbUserDO user) {
        int rows = userDao.insert(user);
        return rows;
    }

    @Override
    public int update(HashMap param) {
        int rows = userDao.update(param);
        return rows;
    }

    @Override
    public int deleteUserByIds(Integer[] ids) {
        int rows = userDao.deleteUserByIds(ids);
        return rows;
    }

    @Override
    public int updateStatus(HashMap param) {
        int rows = userDao.updateStatus(param);
        return rows;
    }

    private String getOpenId(String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        HashMap map = new HashMap();
        //把微信小程序的appId、appSecret、code存到hashmap里
        map.put("appid", appId);
        map.put("secret", appSecret);
        map.put("js_code", code);
        map.put("grant_type", "authorization_code");
        //把url和存到map里的数据生成一个response请求
        String response = HttpUtil.post(url, map);
        //把请求转成json格式
        JSONObject json = JSONUtil.parseObj(response);
        //把json再生成位openId
        String openId = json.getStr("openid");
        //如果openId为空或者openId的长度为0
        if (openId == null || openId.length() == 0) {
            //抛出异常
            throw new RuntimeException("临时登陆凭证错误");
        }
        return openId;
    }

    @Override
    public ArrayList<String> searchUserRoles(int userId) {
        ArrayList<String> list = userDao.searchUserRoles(userId);
        return list;
    }
}
