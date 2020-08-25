package ru.aleksandr.boitsov.CRUDSpringBoot.service;

import ru.aleksandr.boitsov.CRUDSpringBoot.model.Role;


public interface RoleService {

    Role findRoleByName(String name);

}
