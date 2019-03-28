package com.chainalysis.vaadin.spring;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "clientside", layout = MainAppLayout.class)
public class JavaScriptCallView extends VerticalLayout {
    MyCounter counter;
    Label label;

    public JavaScriptCallView() {
        add(new Label("Invokes the methods decrease/increaseCounter() in the " +
                "web component on the client side"));
        counter = new MyCounter();
        add(counter);

        add(new HorizontalLayout(
                new Button("decreaseCounter()",
                        e1 -> counter.decreaseCounter()
                ),
                new Button("increaseCounter()",
                        e1 -> counter.increaseCounter()
                )
        ));

        label = new Label();
        add(label);

        counter.addCountListener(e -> updateLabelText());
        updateLabelText();
    }

    private void updateLabelText() {
        label.setText("The counter is now " + counter.getValue());
    }
}
