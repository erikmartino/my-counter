package com.chainalysis.vaadin.spring;

import com.chainalysis.vaadin.swagger.model.Usersummary;

import javax.validation.constraints.Email;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class UserBean {
    final Usersummary user;
    String userName;

    String fullName;

    public UserBean(Usersummary user) {
        this.user = user;
    }

    public UserBean() {
        this(new Usersummary());
    }

    @Email
    public String getUserName() {
        return user.getUserName();
    }

    public void setUserName(String userName) {
        this.user.setUserName(userName);
    }

    public String getFullName() {
        return user.getFullName();
    }

    public void setFullName(String fullName) {
        this.user.setFullName(fullName);
    }

    public LocalDate getCreationDate() {
        return toLocalDate(user.getCreationDate());
    }


    public LocalDate getExpirationDate() {
        return toLocalDate(user.getCreationDate());
    }

    public void setExpirationDate(LocalDate date) {
        user.setExpirationDate(toMillis(date));
    }

    private long toMillis(LocalDate date) {
        return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime();
    }

    public LocalDate getLastActivity() {
        return toLocalDate(user.getLastActivity());
    }

    private LocalDate toLocalDate(Long date) {
        if (date == null || date == 0) {
            return null;
        }
        return Instant.ofEpochMilli(date).atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
