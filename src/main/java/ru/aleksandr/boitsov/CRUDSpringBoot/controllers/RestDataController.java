package ru.aleksandr.boitsov.CRUDSpringBoot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.aleksandr.boitsov.CRUDSpringBoot.model.User;
import ru.aleksandr.boitsov.CRUDSpringBoot.service.RoleService;
import ru.aleksandr.boitsov.CRUDSpringBoot.service.UserService;

import java.util.List;

@RestController
public class RestDataController {
//    private final Logger logger = LoggerFactory.getLogger(RestDataController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    //Выводим всех пользователей
    @GetMapping(value = "/rest/all")
    public List<User> getAllUsers() {
        return userService.findAll();
    }


    //Вычисляем по id
    @GetMapping(value = "/rest/user/{id}")
    public User getUser(@PathVariable("id") Long id) {
        return userService.findById(id);
    }


    //Обновляем таблицу пользователей
//    @GetMapping(value = "/rest/update")
//    public Iterable<User> updateUserTable() {
//        return userService.findAll();
//    }











    //Добавляем пользователя
    @PostMapping(value = "/rest/addUserRest")
    public ResponseEntity<User> editNewUser(@RequestBody User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userService.save(user);
        return ResponseEntity.ok().body(user);
    }













    //Редактируем пользователя
    @PutMapping(value = "/rest/editSave")
    public ResponseEntity<User> addNewUser(@RequestBody User user) {

        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userService.save(user);
        return ResponseEntity.ok().body(user);
    }


    //Удаляем пользователя
    @DeleteMapping("/rest/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
    }


}
