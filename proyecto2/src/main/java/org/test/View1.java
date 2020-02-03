package org.test;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "view1", layout = MainAppLayout.class)
public class View1 extends VerticalLayout {
    public View1() {


        add (new Button("PUTA"));
    }
}

