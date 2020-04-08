package com.siwz.web.actions;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

@Route("hello")
public class HelloAction extends VerticalLayout {

    public HelloAction() {
        TextField textField = new TextField("Podaj ID pracownika: ");
        Button button = new Button("Szukaj", new Icon(VaadinIcon.ACADEMY_CAP));
        Label label = new Label();

        button.addClickListener(clickEvent -> {
            try {
                label.setText(null);
                URL getEmployeeEndpoint = new URL("http://localhost:8900/employees/" + textField.getValue());
                InputStreamReader employeeReader = new InputStreamReader(getEmployeeEndpoint.openStream());
                JsonObject jsonObject = new JsonParser().parse(employeeReader).getAsJsonObject();
                label.setText("Imię: " + jsonObject.get("firstName").getAsString() + ", nazwisko: " + jsonObject.get("lastName").getAsString());
            } catch(IOException e) {
                if(e.getClass().getName().contains("NotFound")) {
                    label.setText("Nie znaleziono pracownika");
                } else {
                    label.setText("Inny błąd serwera");
                }
            }
        });
        add(textField, button, label);
    }
}
