package ru.aleksandr.boitsov.CRUDSpringBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aleksandr.boitsov.CRUDSpringBoot.model.Role;
import ru.aleksandr.boitsov.CRUDSpringBoot.model.User;
import ru.aleksandr.boitsov.CRUDSpringBoot.repository.RoleRepository;
import ru.aleksandr.boitsov.CRUDSpringBoot.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, RoleService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Transactional
    @Override
    public <S extends User> S save(S user) {
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public User findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.get();
    }


    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }


    @Transactional
    @Override
    public String deleteById(Long id) {
        userRepository.deleteById(id);
        return null;
    }

    @Transactional
    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findRoleByName(name);
    }
}
