package org.test;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "view5", layout = MainAppLayout.class)

public class View5  extends VerticalLayout {
    public View5(){
        add(new Button("polla"));
    }
}
