package org.test;

import com.vaadin.flow.component.Key;

import com.vaadin.flow.component.button.Button;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;

import com.vaadin.flow.component.html.Label;

import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.PWA;



import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;


/**
 * The main view contains a button and a click listener.
 */
@Route("Login")
@PWA(name = "My Application", shortName = "My Application")
@CssImport("./styles/shared-styles.css")
@HtmlImport("frontend://bower_components/iron-form/iron-form.html") //



public class Login extends VerticalLayout  {
    TextField userNameTextField;
    PasswordField passwordField;

    public Login() {
        SetMainLogo();
        SetLoginComponents();
        SetSecondaryLogo();
    }

    public void SetMainLogo(){
        Div Logo1 = new Div();
        Icon logo = new Icon(VaadinIcon.CUBE);
        logo.setColor("hsl(214, 90%, 52%)");
        logo.setSize("18%");
        Logo1.add(logo);
        Logo1.getStyle().set("marginLeft", "730px");
        Logo1.getStyle().set("marginTop","150px");
        add(Logo1);
    }

    public void SetSecondaryLogo(){
        Icon logoV = new Icon(VaadinIcon.FORWARD);
        logoV.getStyle().set("cursor", "pointer");
        logoV.setColor("hsl(214, 90%, 52%)");
        logoV.setSize("3%");
        logoV.addClickListener(
                event -> getUI().ifPresent(ui -> ui.navigate("home"+"/"+8)));

        Div logoVV = new Div();
        logoVV.add(logoV);
        add(logoVV);
        logoVV.getStyle().set("marginLeft", "700px");
    }

    public void SetLoginComponents(){
        userNameTextField = new TextField();
        userNameTextField.getElement().setAttribute("name", "username"); //
        passwordField = new PasswordField();
        passwordField.getElement().setAttribute("name", "password"); //
        Button submitButton = new Button("Login");
        submitButton.addClickShortcut(Key.ENTER);
        submitButton.addClickListener(e->auth());


        FormLayout formLayout = new FormLayout(); //
        formLayout.add(userNameTextField, passwordField, submitButton);

        Element formElement = new Element("form"); //
        formElement.setAttribute("method", "post");
        formElement.setAttribute("action", "login");
        formElement.appendChild(formLayout.getElement());

        Element ironForm = new Element("iron-form"); //
        ironForm.setAttribute("id", "ironform");
        ironForm.setAttribute("allow-redirect", true); //
        ironForm.appendChild(formElement);

        getElement().appendChild(ironForm); //

        setClassName("login-view");

        com.vaadin.flow.component.html.Label correo = new Label("Correo Electrónico");
        com.vaadin.flow.component.html.Label contra = new Label("Contraseña");

        Div pedirnombre = new Div();
        pedirnombre.add(correo);
        add(pedirnombre);
        pedirnombre.getStyle().set("marginLeft", "700px");
        pedirnombre.getStyle().set("marginTop","15px");

        Div datonombre = new Div();
        datonombre.add(userNameTextField);
        add(datonombre);
        datonombre.getStyle().set("marginLeft", "700px");

        Div pedircontra = new Div();
        pedircontra.add(contra);
        add(pedircontra);
        pedircontra.getStyle().set("marginLeft", "700px");

        Div datopass = new Div();
        datopass.add(passwordField);
        add(datopass);
        datopass.getStyle().set("marginLeft", "700px");
        datopass.getStyle().set("font-size", "20px");

        Div botonsito = new Div();
        botonsito.add(submitButton);
        add(botonsito);
        botonsito.getStyle().set("marginLeft", "700px");
    }

    public String auth(){
        //Conexion
        com.vaadin.flow.component.html.Label fallo = new Label("Datos incorrectos");
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://192.168.43.182:8080/OdooConnection-0.0.1-SNAPSHOT/rest/invoice/authenticate?username="+userNameTextField.getValue().toString()+"&password="+passwordField.getValue().toString());
        String s = target.request().get(String.class);
        if (Integer.parseInt(s) > 0){
            getUI().ifPresent(ui -> ui.navigate("MainView"+"/"+s));
        }else{
            Notification notification = new Notification(
                    "Datos incorrectos.", 2000,
                    Notification.Position.MIDDLE);
            notification.open();
        }
        return s;
    }

    public String authtest(String usuario, String contraseña){
        //Conexion
        com.vaadin.flow.component.html.Label fallo = new Label("Datos incorrectos");
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://192.168.43.182:8080/OdooConnection-0.0.1-SNAPSHOT/rest/invoice/authenticate?username="+usuario+"&password="+contraseña);
        String s = target.request().get(String.class);

        return s;
    }

}

