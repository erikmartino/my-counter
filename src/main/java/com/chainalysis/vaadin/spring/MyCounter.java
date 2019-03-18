package com.chainalysis.vaadin.spring;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Synchronize;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.dom.DisabledUpdateMode;

@Tag("my-counter")
@HtmlImport("frontend://my-counter/my-counter.html")
public class MyCounter extends Component {

    private String value;

    public MyCounter() {

    }

    @Synchronize(value = "change", property = "value")
    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
        getElement().setAttribute("value", value);
    }
}
