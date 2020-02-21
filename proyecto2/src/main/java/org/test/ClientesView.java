package org.test;


import com.github.appreciated.card.action.ActionButton;
import com.github.appreciated.card.action.Actions;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.json.JSONArray;



import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import java.io.ByteArrayInputStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Route(value = "clientes", layout = MainAppLayout.class)


public class ClientesView extends VerticalLayout {
    @Override
    public void setSpacing(boolean spacing) {
        super.setSpacing(true);
    }

    FormLayout fl = new FormLayout();

    public ClientesView() throws IOException {
    Logo();
    Client client = ClientBuilder.newClient();
    WebTarget target = client.target("http://192.168.43.182:8080/OdooConnection-0.0.1-SNAPSHOT/rest/customer/getCustomers?userId=2");
    String s = target.request().get(String.class);
    JSONArray jsonArray = new JSONArray(s);
    client.close();
    crearusuarios(jsonArray);
    botonclienteinfo();
    }

    public void Logo (){
        Icon logoV = new Icon(VaadinIcon.GROUP);
        logoV.getStyle().set("cursor", "pointer");
        logoV.setColor("hsl(214, 90%, 52%)");
        logoV.setSize("5%");
        logoV.getStyle().set("marginLeft", "600px");
        logoV.getStyle().set("marginTop","10px");
        logoV.getStyle().set("marginBot","20px");
        Div divtexto = new Div();
        divtexto.add(logoV);
        add(divtexto);
    }

    public void  crearusuarios(JSONArray jsonArray) throws IOException {
        List<Cliente> Cliente = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            Cliente.add(new Cliente(jsonArray.getJSONObject(i).getString("image"),
                    jsonArray.getJSONObject(i).getString("website"),
                    jsonArray.getJSONObject(i).getString("phone"),
                    jsonArray.getJSONObject(i).getString("street"),
                    jsonArray.getJSONObject(i).getString("name"),
                    jsonArray.getJSONObject(i).getString("vat"),
                    jsonArray.getJSONObject(i).getInt("id"),
                    jsonArray.getJSONObject(i).getString("email")));
        }
        addCards(Cliente);
    }
    private void addCards(List<Cliente> clientes) throws IOException {

        for (Cliente ClienteOBJ : clientes) {
            byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(ClienteOBJ.getImage());
            StreamResource resource = new StreamResource("a.png", () -> new ByteArrayInputStream(imageBytes));
            Image image = new Image((resource), "a.png");
            image.setWidth("80px");
            image.setHeight("80px");
            //Image image1 = new Image(fileImage.getAbsolutePath(),"a.png");
            com.github.appreciated.card.Card card = new com.github.appreciated.card.Card(
                    image,
                    new H4 (ClienteOBJ.getName()),
                    new Label(ClienteOBJ.getStreet()),
                    new Actions(
                            new ActionButton("Detalles", event -> {
                                getUI().ifPresent(ui -> ui.navigate("cliente" + "/" + (ClienteOBJ.getIdcliente())));
                            })
                    )
            );
            card.setHeight("200px");
            card.setWidth("30");
            card.setBackground("hsla(214, 53%, 23%, 0.16)");
            fl.add(card);
            add(fl);
        }
    }
    public void botonclienteinfo(){
        Button boton = new Button ("Informe de Clientes");
        boton.addClickListener(e-> {
            try {
                client();
            } catch (JRException ex) {
                ex.printStackTrace();
            }
        });

        Div botondiv = new Div();
        botondiv.getStyle().set("marginLeft", "530px");
        botondiv.add(boton);
        botondiv.getStyle().set("marginTop","20px");
        add(botondiv);
    }
    public boolean client() throws JRException {
        boolean payaso =false;
        java.io.InputStream inputStream = null;
        Conexion conexion1 = new Conexion();
        try {
            inputStream = new FileInputStream("src/main/clientes.jrxml"); //
            Map parameters = new HashMap();
            JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,conexion1.conexionmysql());
            JasperExportManager.exportReportToPdfFile(jasperPrint, "src/clientes.pdf");
            payaso =true;
        } catch (FileNotFoundException ex) {
            payaso =false;
        }
        return  payaso;
    }
}
