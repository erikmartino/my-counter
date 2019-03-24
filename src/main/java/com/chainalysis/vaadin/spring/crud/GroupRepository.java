package com.chainalysis.vaadin.spring.crud;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

/**
 * @author Alejandro Duarte
 */
@Component
public class GroupRepository {
    List<Group> groups = LongStream.range(0, 10)
            .mapToObj(i -> Group.builder()
                    .id(i)
                    .admin(i % 3 == 0)
                    .name("Group #" + i)
                    .build()
            ).collect(toList());

    public List<Group> findAll() {
        return groups;
    }

    public Group save(Group group) {
        return group;
    }

}
