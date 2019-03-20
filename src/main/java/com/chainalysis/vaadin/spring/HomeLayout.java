package com.chainalysis.vaadin.spring;

import com.vaadin.flow.router.Route;

@Route(value = "", layout = MainAppLayout.class)
public class HomeLayout extends BaseView {

    public HomeLayout() {
        add(new MyCounter());
    }

}
