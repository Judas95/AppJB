package org.test;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "view3", layout = MainAppLayout.class)

public class View3 extends VerticalLayout {
    public View3(){
        add(new Button("polla"));
    }

}
