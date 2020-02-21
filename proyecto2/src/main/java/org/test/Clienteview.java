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
import org.apache.commons.collections.FactoryUtils;
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
        invoiceTable(s);
    }

    private void invoiceTable(String s) {
        List<Factura> invoiceList = new ArrayList<>();
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://192.168.43.182:8080/OdooConnection-0.0.1-SNAPSHOT/rest/invoice/getInvoicesByCustomer?userId="+Integer.parseInt(algo.getObject())+"&customerId="+Integer.parseInt(s));
        String s1 = target.request().get(String.class);
        client.close();
        JSONArray jsonArrayInvoice = new JSONArray(s1);
        for (int i = 0; i < jsonArrayInvoice.length(); i++) {
            invoiceList.add(new Factura(jsonArrayInvoice.getJSONObject(i).getInt("id"),
                    jsonArrayInvoice.getJSONObject(i).getString("vendor_display_name"),
                    LocalDate.parse(jsonArrayInvoice.getJSONObject(i).getString("date_invoice")),
                    LocalDate.parse(jsonArrayInvoice.getJSONObject(i).getString("date_due")),
                    jsonArrayInvoice.getJSONObject(i).getDouble("amount_untaxed"),
                    jsonArrayInvoice.getJSONObject(i).getDouble("amount_total"),
                    (Integer) jsonArrayInvoice.getJSONObject(i).getJSONArray("partner_id").get(0)));
        }
        Grid<Factura> invoiceGrid = new Grid<>(Factura.class);
        invoiceGrid.setItems(invoiceList);
        invoiceGrid.removeColumnByKey("idfactura");
        invoiceGrid.removeColumnByKey("cliente");
        invoiceGrid.removeColumnByKey("idcliente");
        invoiceGrid.setColumns( "facturado", "vencimiento", "precio", "total");
        add(invoiceGrid);
        invoiceGrid.addItemDoubleClickListener(event-> getUI().ifPresent(ui -> ui.navigate("formulariofactura" + "/" + event.getItem().getIdfactura())));
    }




}
