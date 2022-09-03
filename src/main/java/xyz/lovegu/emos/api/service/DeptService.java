package xyz.lovegu.emos.api.service;

import xyz.lovegu.emos.api.db.dataobject.TbDeptDO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface DeptService {

    public ArrayList<HashMap> searchAllDept();

    public HashMap searchById(int id);

    public List<HashMap> searchDeptList(HashMap param);

    public int insertDept(TbDeptDO dept);

    public int updateDept(HashMap param);

    public int deleteDeptByIds(Integer[] ids);
}
