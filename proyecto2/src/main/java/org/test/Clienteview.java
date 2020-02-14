package org.test;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.vaadin.flow.helper.HasUrlParameterMapping;
import org.vaadin.flow.helper.UrlParameter;
import org.vaadin.flow.helper.UrlParameterMapping;

@Route("clientes")
@UrlParameterMapping(":idcliente")

public class Clienteview extends VerticalLayout implements HasUrlParameterMapping {

    @UrlParameter
    public String idcliente;

    public Clienteview(){
        com.vaadin.flow.component.html.Label label1= new Label("Yikes.");
        Div divtexto = new Div();
        divtexto.add(label1);
        add(divtexto);
        Home();
        Imagen();
        Texto();
        video();
    }

    public void Home (){
        Icon logoV = new Icon(VaadinIcon.HOME);
        logoV.getStyle().set("cursor", "pointer");
        logoV.setColor("hsl(214, 90%, 52%)");
        logoV.setSize("5%");
        logoV.getStyle().set("marginLeft", "600px");
        logoV.getStyle().set("marginTop","10px");
        add(logoV);
    }

    public void Imagen(){
        Image image = new Image("https://eaeprogramases.cdnstatics.com/sites/default/files/styles/cabecera_post/public/post/q6.jpg?itok=rV8Mu9KW", "DummyImage");
        image.getStyle().set("marginLeft", "300px");
        image.getStyle().set("size","20%");
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
        Anchor anchor = new Anchor("https://vaadin.com", "Click aquí para saber mas.");
        Div divlink = new Div();
        divlink.add(anchor);
        divlink.getStyle().set("marginLeft", "530px");
        add(divlink);
    }




}
