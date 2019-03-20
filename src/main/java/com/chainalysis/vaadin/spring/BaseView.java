package com.chainalysis.vaadin.spring;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public abstract class BaseView extends HorizontalLayout {

    public BaseView() {
        setMargin(false);
        setSizeFull();
        getElement().getStyle().set("overflow", "auto");
    }
}
