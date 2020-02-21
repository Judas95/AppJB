package org.test;



import com.vaadin.flow.component.button.Button;

import com.vaadin.flow.component.html.Div;

import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.flow.router.Route;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.vaadin.crudui.crud.CrudListener;
import org.vaadin.crudui.crud.impl.GridCrud;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Route(value = "facturas", layout = MainAppLayout.class)
public class FacturasView extends VerticalLayout {
    GridCrud<Factura> facturas;
    MainAppLayout algo = new MainAppLayout();
    public FacturasView() {
        logofactura();
        facturalist();
        botondetalles();
    }

    public void facturalist (){
         facturas = new GridCrud<>(Factura.class);
         facturas.getGrid().removeColumnByKey("idfactura");
         facturas.getCrudFormFactory().setDisabledProperties("idfactura","cliente","precio","total");

         facturas.setCrudListener(new CrudListener<Factura>() {
            @Override
            public Collection<Factura> findAll() {
                Client client = ClientBuilder.newClient();
                WebTarget target = client.target("http://192.168.43.182:8080/OdooConnection-0.0.1-SNAPSHOT/rest/invoice/getInvoices?userId="+Integer.parseInt(algo.getObject()));
                String s = target.request().get(String.class);
                JSONArray factur = new JSONArray(s);
                client.close();
                List<Factura> factura = new ArrayList<>();
                for (int i = 0; i < factur.length(); i++) {

                    factura.add(new Factura(factur.getJSONObject(i).getInt("id"),
                            factur.getJSONObject(i).getString("vendor_display_name"),
                            LocalDate.parse(factur.getJSONObject(i).getString("date_invoice")),
                            LocalDate.parse(factur.getJSONObject(i).getString("date_due")),
                            factur.getJSONObject(i).getDouble("amount_untaxed"),
                            factur.getJSONObject(i).getDouble("amount_total"),
                            (Integer) factur.getJSONObject(i).getJSONArray("partner_id").get(0)));
                }
                return factura;
            }
            @Override
            public Factura add(Factura factura)
            {if(String.valueOf(factura.getIdcliente()) == null || factura.getFacturado()==null || factura.getVencimiento() == null){
                Notification notification = new Notification(
                        "Datos imcompletos.", 2000,
                        Notification.Position.MIDDLE);
                notification.open();
            }else {try{
                HttpPost post = new HttpPost("http://192.168.43.182:8080/OdooConnection-0.0.1-SNAPSHOT/rest/invoice/createInvoice?userId="+Integer.parseInt(algo.getObject()));
                JSONObject jsonObjectfactura = new JSONObject();
                jsonObjectfactura.put("invoiceId", factura.getIdfactura());
                jsonObjectfactura.put("vendorDisplayName", factura.getCliente());
                jsonObjectfactura.put("date_invoice", factura.getFacturado());
                jsonObjectfactura.put("date_due", String.valueOf(factura.getVencimiento()));
                jsonObjectfactura.put("amount_untaxed", factura.getPrecio());
                jsonObjectfactura.put("amount_total", factura.getTotal());
                jsonObjectfactura.put("partner_id",factura.getIdcliente());

                post.setEntity(new StringEntity(jsonObjectfactura.toString()));
                post.setHeader("Content-type", "application/json");
                try (CloseableHttpClient httpClient = HttpClients.createDefault();
                     CloseableHttpResponse response = httpClient.execute(post)) {
                    System.out.println(EntityUtils.toString(response.getEntity()));
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
                Notification notification = new Notification(
                        "Factura Creada Correctamente.", 2000,
                        Notification.Position.MIDDLE);
                notification.open();}
                return factura;}


                @Override
            public Factura update(Factura factura)
            {
                if(String.valueOf(factura.getIdcliente()) == null || factura.getFacturado()==null || factura.getVencimiento() == null){
                    Notification notification = new Notification(
                            "Datos imcompletos.", 2000,
                            Notification.Position.MIDDLE);
                    notification.open();
                }else {
                    JSONObject facturaJson = new JSONObject();
                    facturaJson.put("invoiceId",factura.getIdfactura());
                    facturaJson.put("vendorDisplayName", factura.getCliente());
                    facturaJson.put("date_invoice",factura.getFacturado());
                    facturaJson.put("date_due", factura.getVencimiento());
                    facturaJson.put("amount_untaxed", factura.getPrecio());
                    facturaJson.put("amount_total",factura.getTotal());
                    facturaJson.put("partner_id",factura.getIdcliente());
                    try{
                        String putEndpoint = "http://192.168.43.182:8080/OdooConnection-0.0.1-SNAPSHOT/rest/invoice/updateInvoice?userId="+Integer.parseInt(algo.getObject())+"&invoiceId="+factura.getIdfactura();
                        CloseableHttpClient httpclient = HttpClients.createDefault();
                        HttpPut httpPut = new HttpPut(putEndpoint);
                        httpPut.setHeader("Accept", "application/json");
                        httpPut.setHeader("Content-type", "application/json");
                        StringEntity params =new StringEntity(facturaJson.toString());
                        httpPut.setEntity(params);
                        try (CloseableHttpClient httpClient = HttpClients.createDefault();
                             CloseableHttpResponse response = httpClient.execute(httpPut)) {
                            System.out.println(EntityUtils.toString(response.getEntity()));
                        }

                    } catch(IOException e){
                        e.printStackTrace();
                    }
                    Notification notification = new Notification(
                            "Datos Actualizados.", 2000,
                            Notification.Position.MIDDLE);
                    notification.open();
                }




                return factura;
            }

            @Override
            public void delete(Factura factura) {
                CloseableHttpClient httpClient  = HttpClients.createDefault();
                HttpDelete httpDelete = new HttpDelete("http://192.168.43.182:8080/OdooConnection-0.0.1-SNAPSHOT/rest/invoice/deleteInvoice?userId="+Integer.parseInt(algo.getObject())+"&invoiceId="+factura.getIdfactura());
            try {
                HttpResponse response = (HttpResponse) httpClient.execute(httpDelete);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
                Notification notification = new Notification(
                        "Borrado correctamente.", 2000,
                        Notification.Position.MIDDLE);
                notification.open();
            }
        });
        add(facturas);
    }

    public void logofactura(){
        Icon logoV = new Icon(VaadinIcon.CHART_GRID);
        logoV.getStyle().set("cursor", "pointer");
        logoV.setColor("hsl(214, 90%, 52%)");
        logoV.setSize("5%");
        logoV.getStyle().set("marginLeft", "600px");
        logoV.getStyle().set("marginTop","10px");

        Div divtexto = new Div();
        divtexto.add(logoV);
        add(divtexto);
    }

    public void botondetalles(){
        Button botondetalles = new Button ("Detalles");
        botondetalles.addClickListener(e-> detalles());
        add(botondetalles);
    }

    public void detalles(){
        getUI().ifPresent(ui -> ui.navigate("formulariofactura"+"/"+facturas.getGrid().asSingleSelect().getValue().getIdfactura()));
    }

}

