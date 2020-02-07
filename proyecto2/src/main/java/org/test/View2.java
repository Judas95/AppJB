package org.test;


import com.fasterxml.jackson.databind.util.JSONPObject;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.crud.Crud;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import elemental.json.JsonObject;
import jdk.internal.net.http.HttpClientBuilderImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import org.vaadin.crudui.crud.CrudListener;
import org.vaadin.crudui.crud.impl.GridCrud;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Route(value = "facturas", layout = MainAppLayout.class)
public class View2 extends VerticalLayout {

    public View2() {

        com.vaadin.flow.component.html.Label label = new Label("Facturas");
        label.getStyle().set("color","hsl(214, 35%, 15%)");
        label.getStyle().set("font-size", "50px");
        label.getStyle().set("color","hsla(214, 90%, 52%, 0.5)");
        label.getStyle().set("font-family","Open Sans");
        label.getStyle().set("marginLeft", "500px");
        add(label);

        GridCrud<Factura> facturas = new GridCrud<>(Factura.class);
        facturas.setCrudListener(new CrudListener<Factura>() {
            @Override
            public Collection<Factura> findAll() {
                Client client = ClientBuilder.newClient();
                WebTarget target = client.target("http://localhost:8081/ApiWow-0.0.1-SNAPSHOT/rest/servidor/getpersonajesbyid?id=");
                String s = target.request().get(String.class);
                JSONObject factur = new JSONObject(s);

                client.close();

                factur.getJSONArray("facturas");
                JSONArray facturaArray = factur.getJSONArray("facturas");
                List<Factura> factura = new ArrayList<>();
                for (int i = 0; i < facturaArray.length(); i++) {


                    factura.add(new Factura(facturaArray.getJSONObject(i).getInt("id"), facturaArray.getJSONObject(i).getInt("fecha"), facturaArray.getJSONObject(i).getInt("Total"),
                            facturaArray.getJSONObject(i).getInt("sinimpuestos"), facturaArray.getJSONObject(i).getInt("impuestos")));
                }
                return factura;

            }

            @Override
            public Factura add(Factura factura) {
                return null;
            }

            @Override
            public Factura update(Factura factura) {
                return null;
            }

            @Override
            public void delete(Factura factura) {

            }


        });



    add(facturas);


    }
}

