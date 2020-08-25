package ru.aleksandr.boitsov.CRUDSpringBoot.service;


import ru.aleksandr.boitsov.CRUDSpringBoot.model.User;

import java.util.List;

public interface UserService {

    User findByLogin(String login);

    List<User> findAll();

    public <S extends User> S save(S s);

    User findById(Long id);

    String deleteById(Long id);
}
