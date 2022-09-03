package xyz.lovegu.emos.api.service.impl;

import xyz.lovegu.emos.api.db.dao.TbRoleDao;
import xyz.lovegu.emos.api.db.dataobject.TbRoleDO;
import xyz.lovegu.emos.api.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final TbRoleDao roleDao;

    @Override
    public ArrayList<HashMap> searchAllRole() {
        ArrayList<HashMap> list = roleDao.searchAllRole();
        return list;
    }

    @Override
    public HashMap searchById(int id) {
        HashMap map = roleDao.searchById(id);
        return map;
    }

    @Override
    public ArrayList<HashMap> searchRoleByList(HashMap param) {
        return roleDao.searchRoleByList(param);
    }

    @Override
    public int insertRole(TbRoleDO role) {
        return roleDao.insertRole(role);
    }

    @Override
    public int updateRole(HashMap param) {
        return roleDao.updateRole(param);
    }

    @Override
    public int deleteRoleByIds(Integer[] ids) {
        return roleDao.deleteRoleByIds(ids);
    }
}
