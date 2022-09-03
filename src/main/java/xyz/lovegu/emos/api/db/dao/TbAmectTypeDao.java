package xyz.lovegu.emos.api.db.dao;

import xyz.lovegu.emos.api.db.dataobject.TbAmectTypeDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface TbAmectTypeDao {
    public ArrayList<TbAmectTypeDO> searchAllAmectType();
}
