package com.chainalysis.vaadin.spring;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

@Route
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
public class MainView extends VerticalLayout {

    public MainView() {
        MyCounter counter = new MyCounter();
        add(counter);

        add(new HorizontalLayout(
                new Button("setValue(87)",
                        e1 -> counter.setValue(87)
                ),
                new Button("decreaseCounter()",
                        e1 -> counter.decreaseCounter()
                ),
                new Button("increaseCounter()",
                        e1 -> counter.increaseCounter()
                ),
                new Button("setValue(getValue()-1)",
                        e1 -> counter.setValue(counter.getValue() - 1)
                ),
                new Button("setValue(getValue()+1)",
                        e1 -> counter.setValue(counter.getValue() + 1)
                )
        ));

        Label label = new Label();
        updateLabelText(counter, label);
        counter.addChangeListener(e -> updateLabelText(counter, label));
        add(label);

    }

    private void updateLabelText(MyCounter counter, Label label) {
        label.setText("The counter is now " + counter.getValue());
    }
}
