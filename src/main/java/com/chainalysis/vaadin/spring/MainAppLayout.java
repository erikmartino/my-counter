package com.chainalysis.vaadin.spring;

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
@PWA(name = "User Management", shortName = "Uman")
public class MainAppLayout extends AppLayoutRouterLayout {
    private DefaultNotificationHolder notifications;
    private DefaultBadgeHolder badge;

    public MainAppLayout() {

        notifications = new DefaultNotificationHolder(newStatus -> {
        });

        badge = new DefaultBadgeHolder();

        Component appBar = AppBarBuilder
                .get()
                .add(new AppBarNotificationButton(VaadinIcon.BELL, notifications))
                .build();

        NavigationElementContainer appMenu = LeftAppMenuBuilder
                .get()
//                .addToSection(new MenuHeaderComponent("Menu-Header",
//                        "Version 2.0.6",
//                        "/frontend/images/logo.png"
//                ), HEADER)
                .add(new LeftNavigationComponent("Home", VaadinIcon.HOME.create(), HomeLayout.class))
                .add(new LeftNavigationComponent("Users", VaadinIcon.USER.create(), UsersLayout.class))
                .add(new LeftNavigationComponent("Organizations", VaadinIcon.GROUP.create(), OrgLayout.class))
                .build();

        init(AppLayoutBuilder
                .get(Behaviour.LEFT_HYBRID)
                .withTitle("App Layout")
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
