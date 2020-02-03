package org.test;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import sun.jvm.hotspot.gc.shared.Space;

import java.awt.*;

/**
 * The main view contains a button and a click listener.
 */
@Route("Login")
@PWA(name = "My Application", shortName = "My Application")
@CssImport("./styles/shared-styles.css")



public class Login extends VerticalLayout  {

    public Login() {
        SetMainLogo();
        SetLoginComponents();
        SetSecondaryLogo();
    }

    public void SetMainLogo(){
        Icon logo = new Icon(VaadinIcon.CUBE);
        logo.setColor("hsla(214, 90%, 52%, 0.5)");
        logo.setSize("18%");
        logo.getStyle().set("marginLeft", "90px");
        add(logo);
    }

    public void SetSecondaryLogo(){
        Icon logoV = new Icon(VaadinIcon.FORWARD);
        logoV.getStyle().set("cursor", "pointer");
        logoV.setColor("hsla(214, 90%, 52%, 0.5)");
        logoV.getStyle().set("marginLeft", "400px");
        logoV.setSize("3%");
        logoV.addClickListener(
                event -> getUI().ifPresent(ui -> ui.navigate("MainView")));

        com.vaadin.flow.component.html.Label label = new Label("Continuar sin iniciar sesión");
        label.getStyle().set("color","hsla(214, 90%, 52%, 0.5)");
        label.getStyle().set("marginLeft", "260px");


        add(logoV);
        add(label);
    }

    public void SetLoginComponents(){
        TextField userNameTextField = new TextField();
        userNameTextField.getElement().setAttribute("name", "username"); //
        PasswordField passwordField = new PasswordField();
        passwordField.getElement().setAttribute("name", "password"); //
        Button submitButton = new Button("Login");

        submitButton.setId("submitbutton"); //
        UI.getCurrent().getPage().executeJavaScript("document.getElementById('submitbutton').addEventListener('click', () => document.getElementById('ironform').submit());"); //


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

    }

}

