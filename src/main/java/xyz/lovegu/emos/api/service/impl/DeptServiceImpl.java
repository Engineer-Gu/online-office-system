package xyz.lovegu.emos.api.service.impl;

import xyz.lovegu.emos.api.db.dao.TbDeptDao;
import xyz.lovegu.emos.api.db.dataobject.TbDeptDO;
import xyz.lovegu.emos.api.service.DeptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 86153
 */
@Service
@RequiredArgsConstructor
public class DeptServiceImpl implements DeptService {

    private final TbDeptDao deptDao;

    @Override
    public ArrayList<HashMap> searchAllDept() {
        ArrayList<HashMap> list = deptDao.searchAllDept();
        return list;
    }

    @Override
    public HashMap searchById(int id) {
        HashMap map = deptDao.searchById(id);
        return map;
    }

    @Override
    public List<HashMap> searchDeptList(HashMap param) {
        List<HashMap> map = deptDao.searchDeptList(param);
        return map;
    }

    @Override
    public int insertDept(TbDeptDO dept) {
        int rows = deptDao.insertDept(dept);
        return rows;
    }

    @Override
    public int updateDept(HashMap param) {
        int rows = deptDao.updateDept(param);
        return rows;
    }

    @Override
    public int deleteDeptByIds(Integer[] ids) {
        int rows = deptDao.deleteDeptByIds(ids);
        return rows;
    }
}
