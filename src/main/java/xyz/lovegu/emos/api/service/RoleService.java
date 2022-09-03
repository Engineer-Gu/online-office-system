package xyz.lovegu.emos.api.service;

import xyz.lovegu.emos.api.db.dataobject.TbRoleDO;

import java.util.ArrayList;
import java.util.HashMap;

public interface RoleService {

    public ArrayList<HashMap> searchAllRole();

    public HashMap searchById(int id);

    public ArrayList<HashMap> searchRoleByList(HashMap param);

    public int insertRole(TbRoleDO role);

    public int updateRole(HashMap param);

    public int deleteRoleByIds(Integer[] ids);
}
