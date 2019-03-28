package com.chainalysis.vaadin.spring;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.stereotype.Component;

@Route(value = "serverside", layout = MainAppLayout.class)
@Component
public class ServerSideLogicView extends VerticalLayout {
    MyCounter counter;
    Label label;

    public ServerSideLogicView() {
        add(new Label("Uses server to client attribute synchronization " +
                "to modify the counters from Java"));

        counter = new MyCounter();
        add(counter);

        add(new HorizontalLayout(
                new Button("setValue(87)",
                        e1 -> counter.setValue(87)
                ),
                new Button("setValue(getValue()-1)",
                        e1 -> counter.setValue(counter.getValue() - 1)
                ),
                new Button("setValue(getValue()+1)",
                        e1 -> counter.setValue(counter.getValue() + 1)
                )
        ));

        label = new Label();
        add(label);

        counter.addCountListener(e -> counterUpdated());
        counterUpdated();
    }

    void counterUpdated() {
        int value = counter.getValue();
        label.setText("The counter is now " + value);
        if (value == 90) {
            Notification.show("The counter was reset to 90");
        }
    }
}
