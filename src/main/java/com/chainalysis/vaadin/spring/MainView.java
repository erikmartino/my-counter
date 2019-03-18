package com.chainalysis.vaadin.spring;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;

import static java.lang.Integer.parseInt;

@Route
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
public class MainView extends VerticalLayout {

    public MainView(@Autowired MessageBean bean) {
        MyCounter counter = new MyCounter();
        add(counter);

        add(new HorizontalLayout(
                new Button("setValue(87)",
                        e1 -> counter.setValue("87")
                ),
                new Button("increaseCounter",
                        e1 -> counter.increaseCounter()
                ),
                new Button("decreaseCounter()",
                        e1 -> counter.decreaseCounter()
                ),
                new Button("setValue(getValue()+1)",
                        e1 -> counter.setValue(""+ (parseInt(counter.getValue()) + 1))
                ),
                new Button("setValue(getValue()-1)",
                        e1 -> counter.setValue(""+(parseInt(counter.getValue()) - 1))
                )
        ));

        Label label = new Label("The counter is now ?");
        counter.addChangeListener(e -> label.setText("The counter is now " + counter.getValue()));
        add(label);

    }
}
