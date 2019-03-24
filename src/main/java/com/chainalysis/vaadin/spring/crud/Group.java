package com.chainalysis.vaadin.spring.crud;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Alejandro Duarte.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Group {
    private Long id;
    private String name;
    private Boolean admin = false;

    @Override
    public String toString() {
        return name;
    }
}
