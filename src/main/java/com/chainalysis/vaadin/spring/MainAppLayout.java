package com.chainalysis.vaadin.spring;

import com.github.appreciated.app.layout.behaviour.AppLayout;
import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.builder.interfaces.NavigationElementContainer;
import com.github.appreciated.app.layout.component.appbar.AppBarBuilder;
import com.github.appreciated.app.layout.component.appmenu.left.LeftNavigationComponent;
import com.github.appreciated.app.layout.component.appmenu.left.builder.LeftAppMenuBuilder;
import com.github.appreciated.app.layout.entity.DefaultBadgeHolder;
import com.github.appreciated.app.layout.notification.DefaultNotificationHolder;
import com.github.appreciated.app.layout.notification.component.AppBarNotificationButton;
import com.github.appreciated.app.layout.router.AppLayoutRouterLayout;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;

@Push
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
@Theme(Material.class)
@PWA(name = "Lightning Talk", shortName = "LightTalk", description = "Demo for lightning talk")
public class MainAppLayout extends AppLayoutRouterLayout {

    public MainAppLayout() {

        DefaultNotificationHolder notifications = new DefaultNotificationHolder(newStatus -> {
        });

        DefaultBadgeHolder badge = new DefaultBadgeHolder();

        Component appBar = AppBarBuilder
                .get()
                .add(new AppBarNotificationButton(VaadinIcon.BELL_O, notifications))
                .build();

        NavigationElementContainer appMenu = LeftAppMenuBuilder
                .get()
//                .addToSection(new MenuHeaderComponent("Menu-Header",
//                        "Version 2.0.6",
//                        "/frontend/images/logo.png"
//                ), HEADER)
                .add(new LeftNavigationComponent("Home", VaadinIcon.HOME.create(),
                        HomeLayout.class))
                .add(new LeftNavigationComponent("Client Invocation", VaadinIcon.AUTOMATION.create(),
                        JavaScriptCallView.class))
                .add(new LeftNavigationComponent("Server side logic", VaadinIcon.ABACUS.create(),
                        ServerSideLogicView.class))
                .build();

        init(AppLayoutBuilder
                .get(Behaviour.LEFT_HYBRID)
                .withTitle("Lightning Talk")
                .withAppBar(appBar)
                .withAppMenu(appMenu)
                .withTheme(Material.class)
                .build());
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
    }
}
