package xyz.lovegu.emos.api.db.dao;

import xyz.lovegu.emos.api.db.dataobject.TbDeptDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Mapper
public interface TbDeptDao {

    public ArrayList<HashMap> searchAllDept();

    public HashMap searchById(int id);

    public List<HashMap> searchDeptList(HashMap param);

    public long selectDeptCount(HashMap map);

    public int insertDept(TbDeptDO dept);

    public int updateDept(HashMap param);

    public int deleteDeptByIds(Integer[] ids);
}