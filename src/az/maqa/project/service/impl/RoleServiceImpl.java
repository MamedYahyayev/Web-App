package az.maqa.project.service.impl;

import az.maqa.project.dao.inter.RoleDao;
import az.maqa.project.model.Role;
import az.maqa.project.service.inter.RoleService;

import java.util.List;

public class RoleServiceImpl implements RoleService {
    private RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> getRoleList() throws Exception {
        return roleDao.getRoleList();
    }
}
