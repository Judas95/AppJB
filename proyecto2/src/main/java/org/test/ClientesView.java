package org.test;

import com.github.appreciated.card.action.ActionButton;
import com.github.appreciated.card.action.Actions;
import com.github.appreciated.card.label.PrimaryLabel;
import com.github.appreciated.card.label.SecondaryLabel;
import com.github.appreciated.card.label.TitleLabel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.json.JSONArray;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Route(value = "view4", layout = MainAppLayout.class)


public class ClientesView extends VerticalLayout {

    FormLayout fl = new FormLayout();

    public ClientesView() throws IOException {
    Logo();

    Client client = ClientBuilder.newClient();
    WebTarget target = client.target("http://192.168.43.182:8080/OdooConnection-0.0.1-SNAPSHOT/rest/customer/getCustomers?userId=2");
    String s = target.request().get(String.class);
    JSONArray jsonArray = new JSONArray(s);
    client.close();
    crearusuarios(jsonArray);
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
            Cliente.add(new Cliente(jsonArray.getJSONObject(i).getInt("id"),
                    jsonArray.getJSONObject(i).getString("name"),
                    jsonArray.getJSONObject(i).getString("website"),
                    jsonArray.getJSONObject(i).getString("street"),
                    jsonArray.getJSONObject(i).getString("vat"),
                    jsonArray.getJSONObject(i).getString("phone"),
                    jsonArray.getJSONObject(i).getString("email")));
        }
        addCards(Cliente);
    }
    private void addCards(List<Cliente> clientes) throws IOException {
        for (Cliente ClienteOBJ : clientes) {
            com.github.appreciated.card.Card card = new com.github.appreciated.card.Card(
                    //new TitleLabel(clienteOBJ.getName()).withWhiteSpaceNoWrap(),
                    //image,
                    new Label(" Cliente"),
                    new PrimaryLabel(ClienteOBJ.getName()),
                    new Label(" Dirección"),
                    new SecondaryLabel(ClienteOBJ.getStreet()),
                    new Actions(
                            new ActionButton("Detalles", event -> {
                                getUI().ifPresent(ui -> ui.navigate("clientes" + "/" + String.valueOf(ClienteOBJ.getIdcliente())));
                            })
                    )
            );
            fl.add(card);
            add(fl);
        }
    }

}
