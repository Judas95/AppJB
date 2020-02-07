package org.test;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import sun.awt.EmbeddedFrame;

import java.awt.*;

@Route(value = "home", layout = MainAppLayout.class)
public class View1 extends VerticalLayout {
    public View1() {
        Home();
        Imagen();
        Texto();
    }

    public void Home (){

        Icon logoV = new Icon(VaadinIcon.HOME);
        logoV.getStyle().set("cursor", "pointer");
        logoV.setColor("hsl(214, 90%, 52%)");
        logoV.setSize("5%");
        logoV.getStyle().set("marginLeft", "500px");
        logoV.getStyle().set("marginTop","10px");
        add(logoV);

        ////label.getStyle().set("color","hsl(214, 35%, 15%)");
        //label.getStyle().set("font-size", "50px");
        //label.getStyle().set("color","hsla(214, 90%, 52%, 0.5)");
        //label.getStyle().set("font-family","Open Sans");
        //label.getStyle().set("marginLeft", "500px");
        //add(label);
    }

    public void Imagen(){
        Image image = new Image("https://eaeprogramases.cdnstatics.com/sites/default/files/styles/cabecera_post/public/post/q6.jpg?itok=rV8Mu9KW", "DummyImage");
        image.getStyle().set("marginLeft", "250px");

        Div divimage = new Div();
        divimage.add(image);
        add(divimage);

    }

    public void Texto(){
        com.vaadin.flow.component.html.Label label1= new Label("Yikes.");

        Div divtexto = new Div();
        divtexto.add(label1);
        add(divtexto);
    }

    public void video(){

    }



}

