package org.test;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "view6", layout = MainAppLayout.class)

public class View6 extends VerticalLayout {
    public View6(){
        add(new Button("polla"));
    }
}
