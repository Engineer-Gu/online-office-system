package xyz.lovegu.emos.api.service.impl;

import xyz.lovegu.emos.api.db.dao.TbPermissionDao;
import xyz.lovegu.emos.api.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final TbPermissionDao permissionDao;

    @Override
    public ArrayList<HashMap> searchAllPermission() {

        ArrayList<HashMap> list = permissionDao.searchAllPermission();
        return list;
    }
}
