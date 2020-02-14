package org.test;

import com.github.appreciated.app.layout.component.applayout.LeftLayouts;
import com.github.appreciated.app.layout.component.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.component.menu.left.builder.LeftAppMenuBuilder;
import com.github.appreciated.app.layout.component.menu.left.items.LeftHeaderItem;
import com.github.appreciated.app.layout.component.menu.left.items.LeftNavigationItem;
import com.github.appreciated.app.layout.component.router.AppLayoutRouterLayout;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

import static com.github.appreciated.app.layout.entity.Section.HEADER;

@Route("MainView")
@Push
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
public class MainAppLayout extends AppLayoutRouterLayout<LeftLayouts.LeftResponsive> implements HasUrlParameter<String > {
    static String algo;
    public MainAppLayout() {
        com.vaadin.flow.component.html.Label label = new Label("CuboDoo");
        label.getStyle().set("marginLeft", "65px");
        label.getStyle().set("color","hsl(214, 90%, 52%)");
        label.getStyle().set("font-size", "70px");
        label.getStyle().set("font-family","Open Sans");
        init(AppLayoutBuilder.get(LeftLayouts.LeftResponsive.class)
                .withTitle(label)
                .withAppMenu(LeftAppMenuBuilder.get()
                        .addToSection(HEADER,
                                new LeftHeaderItem("Bienvenido", "", "https://previews.123rf.com/images/martialred/martialred1710/martialred171000007/88316246-icono-de-vector-de-arte-de-l%C3%ADnea-cubo-tridimensional-o-3d-hexaedro-.jpg")
                        )
                        .add(new LeftNavigationItem("Home", VaadinIcon.HOME.create(), Home.class),
                                new LeftNavigationItem("Facturas", VaadinIcon.COIN_PILES.create(), FacturasView.class),
                                new LeftNavigationItem("Clientes", VaadinIcon.GROUP.create(), ClientesView.class),
                                new LeftNavigationItem("XXX", VaadinIcon.DIAMOND_O.create(), Pruebas.class))
                        .build())
                .build());
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String  o) {
        algo = o;
    }

    public String getObject(){
        return algo;
    }



}
