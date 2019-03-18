package com.chainalysis.vaadin.spring;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.shared.Registration;

@Tag("my-counter")
@HtmlImport("frontend://my-counter/my-counter.html")
public class MyCounter extends Component {
    private static PropertyDescriptor<String, String> VALUE =
            PropertyDescriptors.propertyWithDefault("value", "");

    private String value;


    // binding attributes bidirectional
    @Synchronize(value = "count")
    public String getValue() {
        return get(VALUE);
    }

    public void setValue(String value) {
        set(VALUE, value);
    }

    // Events from client side to server side
    public Registration addChangeListener(
            ComponentEventListener<CountEvent> listener) {
        return addListener(CountEvent.class, listener);
    }

    @DomEvent("count")
    public static class CountEvent extends ComponentEvent<MyCounter> {
        public CountEvent(MyCounter source, boolean fromClient) {
            super(source, fromClient);
        }
    }

    // call methods client side
    public void increaseCounter() {
        getElement().callFunction("increaseCounter");
    }

    public void decreaseCounter() {
        getElement().callFunction("decreaseCounter");
    }
}
