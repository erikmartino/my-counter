package com.chainalysis.vaadin.spring;

import com.chainalysis.vaadin.swagger.ApiException;
import com.chainalysis.vaadin.swagger.api.LegacyApi;
import com.chainalysis.vaadin.swagger.model.Usersummary;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Stream;

@Route(value = "users", layout = MainAppLayout.class)
public class UsersLayout extends BaseView {
    @Autowired
    LegacyApi legacyApi;

    private final Grid<Usersummary> grid;

    public UsersLayout() {
        grid = new Grid<>(Usersummary.class, true);
        DataProvider<Usersummary, Void> dataProvider = DataProvider.fromCallbacks(
                // First callback fetches items based on a query
                query -> {
                    return getElementStream(query.getOffset(), query.getLimit());
                },
                // Second callback fetches the number of items for a query
                query -> {
                    return getElementCount();
                });
        grid.setDataProvider(dataProvider);
        add(grid);
    }

    private int getElementCount() {
        try {
            return legacyApi.listUsers().size();
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }

    private Stream<Usersummary> getElementStream(int offset, int limit) {
        try {
            List<Usersummary> orgs = legacyApi.listUsers();
            return orgs.subList(offset,
                    offset + limit)
                    .stream();
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }
}
