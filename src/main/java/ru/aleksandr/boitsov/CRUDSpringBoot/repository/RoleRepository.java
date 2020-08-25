package ru.aleksandr.boitsov.CRUDSpringBoot.repository;


import org.springframework.data.repository.CrudRepository;
import ru.aleksandr.boitsov.CRUDSpringBoot.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findRoleByName(String name);
}
