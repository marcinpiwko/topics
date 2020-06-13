package com.siwz.web.actions;

import com.siwz.web.repository.impl.UserAuthenticationSQL;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.navigator.ViewChangeListener;



@Route("mainview")
@StyleSheet("/css/mainview.css")
public class MainView extends VerticalLayout {
    private String currentUserName;
    private Label label = new Label();

    public MainView(){
        VerticalLayout mainViewLayout;
        mainViewLayout = new VerticalLayout(label);

        add(mainViewLayout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event){
        currentUserName = (String) VaadinSession.getCurrent().
                getAttribute(UserAuthenticationSQL.AUTHENTICATED_NAME);
        label.setText("Witaj " + currentUserName);
    }
}
