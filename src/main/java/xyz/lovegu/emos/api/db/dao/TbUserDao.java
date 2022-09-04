package xyz.lovegu.emos.api.db.dao;

import xyz.lovegu.emos.api.db.dataobject.TbUserDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

@Mapper
public interface TbUserDao {
    public Set<String> searchUserPermissions(int userId);

    public HashMap searchById(int userId);

    public Integer searchIdByOpenId(String openId);

    public HashMap searchUserSummary(int userId);

    public HashMap searchUserInfo(int userId);

    public Integer searchDeptManagerId(int id);

    public Integer searchGmId();

    public ArrayList<HashMap> searchAllUser();

    public Integer login(HashMap param);

    public int updatePassword(HashMap param);

    public ArrayList<HashMap> searchUserByPage(HashMap param);

    public long searchUserCount(HashMap param);

    public int insert(TbUserDO user);

    public int update(HashMap param);

    public int deleteUserByIds(Integer[] ids);

    public int updateStatus(HashMap param);

    public ArrayList<String> searchUserRoles(int userId);
}