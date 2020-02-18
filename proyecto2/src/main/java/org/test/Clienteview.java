package org.test;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.Route;
import org.json.JSONArray;
import org.vaadin.flow.helper.HasUrlParameterMapping;
import org.vaadin.flow.helper.UrlParameter;
import org.vaadin.flow.helper.UrlParameterMapping;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Route("cliente")


public class Clienteview extends VerticalLayout implements HasUrlParameterMapping {
    MainAppLayout algo = new MainAppLayout();
    FormLayout fl = new FormLayout();
    Button botonbackpage = new Button("Back");

    public Clienteview(){
        botonbackpage.addClickListener(e -> backpage());
        this.add(botonbackpage);
    }

    private void setForm(Cliente cliente ) {
        fl.setResponsiveSteps(
                new FormLayout.ResponsiveStep("20em", 1),
                new FormLayout.ResponsiveStep("20em", 2),
                new FormLayout.ResponsiveStep("20em", 3));



        TextField idcliente = new TextField();
        idcliente.setLabel("Id Cliente");
        idcliente.setValue(String.valueOf(cliente.getIdcliente()));
        idcliente.setEnabled(false);

        TextField name = new TextField();
        name.setLabel("Cliente");
        name.setValue(cliente.getName());
        name.setEnabled(false);

        TextField website = new TextField();
        website.setLabel("Website");
        website.setValue(cliente.getWebsite());
        website.setEnabled(false);

        TextField street = new TextField();
        street.setLabel("Direccion");
        street.setValue(cliente.getStreet());
        street.setEnabled(false);

        TextField cif = new TextField();
        cif.setLabel("CIF");
        cif.setValue(cliente.getVat());
        cif.setEnabled(false);

        TextField email = new TextField();
        email.setLabel("Email");
        email.setValue(cliente.getEmail());
        email.setEnabled(false);

        fl.add(idcliente, 1);
        fl.add(name, 1);
        fl.add(website, 1);
        fl.add(street, 1);
        fl.add(cif, 1);
        fl.add(email, 1);

        add(fl);
    }

    public void backpage() {
        getUI().ifPresent(ui -> ui.navigate("clientes"));
    }

    public Cliente crearcliente(String idcliente) {

        Cliente cliente = null;
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://192.168.43.182:8080/OdooConnection-0.0.1-SNAPSHOT/rest/customer/getCustomerById?userId="+Integer.parseInt(algo.getObject())+"&customerId="+Integer.parseInt(idcliente));
        String s = target.request().get(String.class);
        JSONArray jsonObjectcliente = new JSONArray(s);
        client.close();
        cliente = new Cliente(jsonObjectcliente.getJSONObject(0).getString("image"),
                jsonObjectcliente.getJSONObject(0).getString("website"),
                jsonObjectcliente.getJSONObject(0).getString("phone"),
                jsonObjectcliente.getJSONObject(0).getString("street"),
                jsonObjectcliente.getJSONObject(0).getString("name"),
                jsonObjectcliente.getJSONObject(0).getString("vat"),
                jsonObjectcliente.getJSONObject(0).getInt("id"),
                jsonObjectcliente.getJSONObject(0).getString("email"));

        return cliente;

    }
    @Override
    public void setParameter(BeforeEvent beforeEvent, String s) {
        setForm(crearcliente(s));
        //tablaproductos(s);
    }

    public void listafacturadelcliente(){
        List<Factura> facturas = new ArrayList<>();
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("");
        String s = target.request().get(String.class);




    }


}
