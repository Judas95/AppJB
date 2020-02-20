package org.test;



import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.server.EventTrigger;
import com.vaadin.server.ExternalResource;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.graalvm.compiler.core.common.type.ArithmeticOpTable;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Route(value = "home", layout = MainAppLayout.class)
public class Home extends VerticalLayout {
Button bot1 = new Button();

    public Home() {
        Home();
        Imagen();
        Texto();
        video();
        botoninforme();


    }

    public void botoninforme(){
        Button boton = new Button ("Informe de Facturas");
        boton.addClickListener(e-> {
            try {
                informar();
            } catch (JRException ex) {
                ex.printStackTrace();
            }
        });
        Div botonhelp = new Div();
        Button bot = new Button("help");
        bot.addClickListener(e->ayudar());
        botonhelp.getStyle().set("marginLeft", "590px");
        botonhelp.add(bot);
        botonhelp.getStyle().set("marginTop","50px");
        add(botonhelp);

        Div botondiv = new Div();
        botondiv.getStyle().set("marginLeft", "530px");
        botondiv.add(boton);
        botondiv.getStyle().set("marginTop","20px");
        add(botondiv);
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
        Image image = new Image("https://cronopiosidiomas.com/wp-content/uploads/2019/01/bienvenidos.png", "DummyImage");
        image.getStyle().set("marginLeft", "300px");
        image.getStyle().set("size","10%");
        Div divimage = new Div();
        divimage.add(image);
        add(divimage);
    }

    public void Texto(){
        com.vaadin.flow.component.html.Label label1= new Label("Bienvenido a tu página de confianza para administrar tu contabilidad y distribución.");
        Div divtexto = new Div();
        divtexto.add(label1);
        divtexto.getStyle().set("marginLeft","300px");
        divtexto.getStyle().set("marginTop","20px");
        add(divtexto);
    }

    public void video(){
        Anchor anchor = new Anchor("https://vaadin.com", "Click aquí para saber mas.");
        Div divlink = new Div();
        divlink.add(anchor);
        divlink.getStyle().set("marginLeft", "530px");
        divlink.getStyle().set("marginTop","20px");
        add(divlink);
    }

    public boolean informar() throws JRException {
        boolean payaso =false;
        java.io.InputStream inputStream = null;
        Conexion conexion1 = new Conexion();
        try {
            inputStream = new FileInputStream("src/main/reportito.jrxml"); //
            Map parameters = new HashMap();
            JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,conexion1.conexionmysql());
            JasperExportManager.exportReportToPdfFile(jasperPrint, "src/factura.pdf");
            payaso =true;
        } catch (FileNotFoundException ex) {
            payaso =false;
        }
        return  payaso;
    }

    public void ayudar(){
            getUI().get().getPage().open("http://192.168.203.30:8080/html/Home.html");
}
}

