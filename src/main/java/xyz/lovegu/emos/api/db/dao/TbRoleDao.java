package xyz.lovegu.emos.api.db.dao;

import xyz.lovegu.emos.api.db.dataobject.TbRoleDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;


@Mapper
public interface TbRoleDao {

    public ArrayList<HashMap> searchAllRole();

    public HashMap searchById(int id);

    public ArrayList<HashMap> searchRoleByList(HashMap param);

    public int insertRole(TbRoleDO role);

    public int updateRole(HashMap param);

    public int deleteRoleByIds(Integer[] ids);
}