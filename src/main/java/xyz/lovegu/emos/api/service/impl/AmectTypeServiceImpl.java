package xyz.lovegu.emos.api.service.impl;

import xyz.lovegu.emos.api.db.dao.TbAmectTypeDao;
import xyz.lovegu.emos.api.db.dataobject.TbAmectTypeDO;
import xyz.lovegu.emos.api.service.AmectTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AmectTypeServiceImpl implements AmectTypeService {

    private final TbAmectTypeDao amectTypeDao;

    @Override
    public ArrayList<TbAmectTypeDO> searchAllAmectType() {
        ArrayList<TbAmectTypeDO> list = amectTypeDao.searchAllAmectType();
        return list;
    }
}