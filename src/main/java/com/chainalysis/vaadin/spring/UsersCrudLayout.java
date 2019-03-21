package com.chainalysis.vaadin.spring;

import com.chainalysis.vaadin.swagger.ApiException;
import com.chainalysis.vaadin.swagger.api.LegacyApi;
import com.chainalysis.vaadin.swagger.model.CreateUserRequest;
import com.chainalysis.vaadin.swagger.model.CreateUserResponse;
import com.chainalysis.vaadin.swagger.model.DeleteUserRequest;
import com.chainalysis.vaadin.swagger.model.Usersummary;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.CrudListener;
import org.vaadin.crudui.crud.impl.GridCrud;
import org.vaadin.crudui.form.CrudFormFactory;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Route(value = "users2", layout = MainAppLayout.class)
public class UsersCrudLayout extends BaseView implements CrudListener<UserBean> {

    final LegacyApi api;

    private final GridCrud<UserBean> crud;

    public UsersCrudLayout(LegacyApi api) {
        this.api = api;
        crud = new GridCrud<>(UserBean.class);
        crud.setCrudListener(this);

        CrudFormFactory<UserBean> ff = crud.getCrudFormFactory();

        Grid<UserBean> grid = crud.getGrid();
        grid.removeAllColumns();
        grid.addColumn("userName");
        grid.addColumn("fullName");
        grid.addColumn("expirationDate");
        grid.addColumn("lastActivity");

        ff.setVisibleProperties("userName", "fullName", "expirationDate");
        add(crud);
    }

    public static LocalDate toLocalDate(Long expirationDate) {
        return Instant.ofEpochMilli(expirationDate).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private int getElementCount() {
        try {
            return api.listUsers().size();
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }

    private Stream<Usersummary> getElementStream(int offset, int limit) {
        try {
            List<Usersummary> users = api.listUsers();
            return users.subList(offset,
                    offset + limit)
                    .stream();
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<UserBean> findAll() {
        try {
            return api.listUsers().stream().map(UserBean::new).collect(toList());
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserBean add(UserBean user) {
        CreateUserRequest req = new CreateUserRequest();
        req.setFullName(user.getFullName());
        req.setUserName(user.getUserName());
        req.setOrgName("Foo");
        req.setDaysValid(365);

        try {
            CreateUserResponse newUser = api.createUser(req);
            return api.listUsers().stream().filter(e -> e.getUserName().equals(user.getUserName()))
                    .map(UserBean::new)
                    .findAny()
                    .get();
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserBean update(UserBean u) {
        return u;
    }

    @Override
    public void delete(UserBean u) {
        DeleteUserRequest r = new DeleteUserRequest();
        r.setUserName(u.getUserName());
        try {
            api.deleteUser(r);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }

}
