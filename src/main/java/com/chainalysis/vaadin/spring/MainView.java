package com.chainalysis.vaadin.spring;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

@Route()
@PWA(name = "My Counter", shortName = "My Counter")
@Push
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
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
        counter.addCountListener(e -> updateLabelText(counter, label));
        add(label);

    }

    private void updateLabelText(MyCounter counter, Label label) {
        label.setText("The counter is now " + counter.getValue());
    }
}
