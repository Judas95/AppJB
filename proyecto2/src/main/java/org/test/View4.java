package org.test;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "vies4", layout = MainAppLayout.class)

public class View4 extends VerticalLayout {
    public View4(){
        add(new Button("polla"));
    }
}
