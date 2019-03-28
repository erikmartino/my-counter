package com.chainalysis.vaadin.spring;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.Route;

@Route(value = "", layout = MainAppLayout.class)
public class HomeLayout extends VerticalLayout {

    private final MyCounter myCounter;

    public HomeLayout() {
        Style style = getElement().getStyle();
        style.set("width", "100%");
        style.set("height", "100%");
        style.set("overflow", "auto");

        Div div = new Div();
        add(div);
        String value = "display: flex;width: 100%;height: 100%;justify-content: center;align-items: center;";
        div.getElement().setAttribute("style", value);
        myCounter = new MyCounter();
        div.add(myCounter);
    }

}
