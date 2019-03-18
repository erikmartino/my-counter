package com.chainalysis.vaadin.spring;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;

@Route
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
public class MainView extends VerticalLayout {

    public MainView(@Autowired MessageBean bean) {
        MyCounter counter = new MyCounter();
        add(counter);

        HorizontalLayout hbox = new HorizontalLayout();

        hbox.add(new Button("Decrease",
                e1 -> counter.decreaseCounter()
        ));
        hbox.add(new Button("Reset to 87",
                e1 -> counter.setValue("87")
        ));
        hbox.add(new Button("Increase",
                e1 -> counter.increaseCounter()
        ));
        add(hbox);

        Label label = new Label(counter.getValue());
        counter.addChangeListener(e -> label.setText("The counter is now "+counter.getValue()));
        add(label);

    }

}
