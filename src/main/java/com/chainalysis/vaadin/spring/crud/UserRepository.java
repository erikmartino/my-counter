package com.chainalysis.vaadin.spring.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * @author Alejandro Duarte
 */
@Component
public class UserRepository {

    @Autowired
    GroupRepository groups;

    List<User> users;

    @PostConstruct
    public void init() {
        List<Group> all = groups.findAll();
        users = IntStream.range(0, 100).mapToObj(i ->
                User.builder().active(i % 2 == 0)
                        .id(i)
                        .birthDate(LocalDate.now())
                        .email("user" + i + "@example.com")
                        .groups(new HashSet<>(all.subList(0,5)))
                        .mainGroup(all.get(0))
                        .maritalStatus(MaritalStatus.MARRIED)
                        .name("User #" + i)
                        .password("123")
                        .phoneNumber(5555555)
                        .build()).collect(toList());
    }


    public List<User> findAll() {
        return users;
    }

    public List<User> findByNameLike(String name, Group group, int offset, int limit) {
        return users.stream().filter(e -> e.getName().contains(name))
                .skip(offset)
                .limit(limit)
                .collect(toList());
    }

    public int countByNameLike(String name, Group group) {
        return (int) users.stream().filter(e -> e.getName().contains(name)).count();
    }

    public User save(User user) {
        return user;
    }

    public void delete(User user) {
        users.remove(user);
    }
}
