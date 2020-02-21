package org.test;



import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.flow.component.textfield.TextField;

import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
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
import java.text.Normalizer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Route("formulariofactura")



public class FormularioView extends VerticalLayout implements HasUrlParameter<String> {
    MainAppLayout algo = new MainAppLayout();

    FormLayout fl = new FormLayout();

    Button botonbackpage = new Button("Back");
    Button bot = new Button("Back");
    TextField cliente;
    public FormularioView() {

        botonbackpage.addClickListener(e -> backpage());
        this.add(botonbackpage);

    }
    private void setForm(Factura factura ) {
        fl.setResponsiveSteps(
                new FormLayout.ResponsiveStep("20em", 1),
                new FormLayout.ResponsiveStep("20em", 2),
                new FormLayout.ResponsiveStep("20em", 3));
        TextField idfactura = new TextField();
        idfactura.setLabel("Id Factura");
        idfactura.setValue(String.valueOf(factura.getIdfactura()));
        idfactura.setEnabled(false);

        cliente = new TextField();
        cliente.setLabel("Cliente");
        cliente.setValue(factura.getCliente());
        cliente.setEnabled(false);



        TextField idcliente = new TextField();
        idcliente.setLabel("Id Cliente");
        idcliente.setValue(String.valueOf(factura.getIdcliente()));
        idcliente.setEnabled(false);

        TextField precio = new TextField();
        precio.setLabel("Precio");
        precio.setValue(String.valueOf(factura.getTotal()));
        precio.setEnabled(false);

        TextField facturado = new TextField();
        facturado.setLabel("Facturado");
        facturado.setValue(String.valueOf(factura.getFacturado()));
        facturado.setEnabled(false);


        TextField vencimiento = new TextField();
        vencimiento.setLabel("Vencimiento");
        vencimiento.setValue((String.valueOf(factura.getVencimiento())));
        vencimiento.setEnabled(false);



        fl.add(idfactura, 1);
        fl.add(cliente, 1);
        fl.add(idcliente, 1);
        fl.add(facturado, 1);
        fl.add(facturado, 1);
        fl.add(precio, 1);

        add(fl);
    }

    public void backpage() {
        getUI().ifPresent(ui -> ui.navigate("facturas"));
    }


    public Factura crearfactura(String idFactura) {

            Factura factura = null;
            Client client = ClientBuilder.newClient();

            WebTarget target = client.target("http://192.168.43.182:8080/OdooConnection-0.0.1-SNAPSHOT/rest/invoice/getInvoice?userId="+Integer.parseInt(algo.getObject())+"&invoiceId="+Integer.parseInt(idFactura));
            String s = target.request().get(String.class);
            JSONArray jsonObjectfactura = new JSONArray(s);
            client.close();
            factura = new Factura(jsonObjectfactura.getJSONObject(0).getInt("id"),
            jsonObjectfactura.getJSONObject(0).getString("vendor_display_name"),
            LocalDate.parse(jsonObjectfactura.getJSONObject(0).getString("date_invoice")),
            LocalDate.parse(jsonObjectfactura.getJSONObject(0).getString("date_due")),
            jsonObjectfactura.getJSONObject(0).getDouble("amount_untaxed"),
            jsonObjectfactura.getJSONObject(0).getDouble("amount_total"),
            (Integer) jsonObjectfactura.getJSONObject(0).getJSONArray("partner_id").get(0));

         return factura;

    }

    public void tablaproductos(String idFactura ){
        GridCrud<Producto> productos = new GridCrud<>(Producto.class);
        productos.getCrudFormFactory().setDisabledProperties("name","invoiceLineId","preciofinal");
        productos.setCrudListener(new CrudListener<Producto>() {
            @Override
            public Collection<Producto> findAll() {
                Client client = ClientBuilder.newClient();
                WebTarget target = client.target("http://192.168.43.182:8080/OdooConnection-0.0.1-SNAPSHOT/rest/product/getProductsFromInvoice?userId="+Integer.parseInt(algo.getObject())+"&invoiceId="+Integer.parseInt(idFactura));
                String s = target.request().get(String.class);
                System.out.println(s);
                JSONArray product = new JSONArray(s);
                client.close();
                List<Producto> producto = new ArrayList<>();
                for (int i = 0; i < product.length(); i++) {

                    producto.add(new Producto(product.getJSONObject(i).getInt("price_unit"),
                            product.getJSONObject(i).getInt("quantity"),
                            product.getJSONObject(i).getString("name"),
                            (Integer) product.getJSONObject(i).getJSONArray("product_id").get(0),
                            product.getJSONObject(i).getFloat("price_total"),
                            product.getJSONObject(i).getInt("id"),
                            (String)  product.getJSONObject(i).getJSONArray("product_id").get(1)));
                }
                return producto;
            }

                @Override
            public Producto add(Producto producto) {
                    if(String.valueOf(producto.getCantidad()) == null || producto.getDescripcion()==null || (String.valueOf(producto.getIdproducto())==null || (String.valueOf(producto.getPreciounidad()))==null)){
                        Notification notification = new Notification(
                                "Datos imcompletos.", 2000,
                                Notification.Position.MIDDLE);
                        notification.open();
                    }else{try{
                        HttpPost post = new HttpPost("http://192.168.43.182:8080/OdooConnection-0.0.1-SNAPSHOT/rest/product/addProductToInvoice?userId="+Integer.parseInt(algo.getObject()));
                        JSONObject jsonObjectproducto = new JSONObject();
                        jsonObjectproducto.put("name", producto.getDescripcion());
                        jsonObjectproducto.put("invoice_id", idFactura);
                        jsonObjectproducto.put("product_id", producto.getIdproducto());
                        jsonObjectproducto.put("price_unit", producto.getPreciounidad());
                        jsonObjectproducto.put("quantity",producto.getCantidad());


                        post.setEntity(new StringEntity(jsonObjectproducto.toString()));
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
                                "Producto Creado Correctamente.", 2000,
                                Notification.Position.MIDDLE);
                        notification.open();}

                return producto;
            }

            @Override
            public Producto update(Producto producto) {
                if(String.valueOf(producto.getCantidad()) == null || producto.getDescripcion()==null|| producto.getDescripcion()==null || (String.valueOf(producto.getIdproducto())==null || (String.valueOf(producto.getPreciounidad()))==null )) {
                    Notification notification = new Notification(
                            "Datos imcompletos.", 2000,
                            Notification.Position.MIDDLE);
                    notification.open();
                }else{JSONObject facturaJson = new JSONObject();
                        facturaJson.put("name",producto.getDescripcion());
                        facturaJson.put("invoice_line_id", producto.getInvoiceLineId());
                        facturaJson.put("invoice_id",idFactura);
                        facturaJson.put("product_id",producto.getIdproducto());
                        facturaJson.put("price_unit", producto.getPreciounidad());
                        facturaJson.put("quantity", producto.getCantidad());

                        try{
                            String putEndpoint = "http://192.168.43.182:8080/OdooConnection-0.0.1-SNAPSHOT/rest/product/updateProductWithinInvoice?userId="+Integer.parseInt(algo.getObject());
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
                        Notification notification1 = new Notification(
                                "Producto Actualizado Correctamente.", 2000,
                                Notification.Position.MIDDLE);
                        notification1.open();}

                return producto;
            }

            @Override
            public void delete(Producto producto) {
                CloseableHttpClient httpClient  = HttpClients.createDefault();
                HttpDelete httpDelete = new HttpDelete("http://192.168.43.182:8080/OdooConnection-0.0.1-SNAPSHOT/rest/product/deleteProduct?userId="+Integer.parseInt(algo.getObject())+"&invoiceLineId="+producto.getInvoiceLineId());
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
                        "Producto Eliminado correctamente.", 2000,
                        Notification.Position.MIDDLE);
                notification.open();
            }


        });
        add(productos);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String s) {
        setForm(crearfactura(s));
        tablaproductos(s);
    }
}





