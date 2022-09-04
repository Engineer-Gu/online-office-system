package xyz.lovegu.emos.api.service;

import xyz.lovegu.emos.api.common.util.PageUtils;
import xyz.lovegu.emos.api.db.dataobject.TbUserDO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public interface UserService {
    public HashMap createQrCode();

    public boolean checkQrCode(String code,String uuid);

    public HashMap wechatLogin(String uuid);

    public Set<String> searchUserPermissions(int userId);

    public HashMap searchUserSummary(int userId);

    public HashMap searchById(int userId);

    public ArrayList<HashMap> searchAllUser();

    public Integer login(HashMap param);

    public int updatePassword(HashMap param);

    public PageUtils searchUserByPage(HashMap param);

    public long searchUserCount(HashMap param);

    public int insert(TbUserDO user);

    public int update(HashMap param);

    public int deleteUserByIds(Integer[] ids);

    public int updateStatus(HashMap param);

    public ArrayList<String> searchUserRoles(int userId);
}
