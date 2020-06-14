package com.siwz.web.actions;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@StyleSheet("/css/submitsubject.css")
@PageTitle("Submit Subject")
@Route("submitsubject")

public class SubmitSubject extends VerticalLayout {
    private Button button = new Button("SUBMIT");
    private TextField nameField = new TextField();
    private TextField descriptionField = new TextField();
    private TextField ectsField = new TextField();

    public SubmitSubject() {
            Label text = new Label("Submit new Subject");
            text.addClassName("description");

            nameField.setLabel("Subject name");
            nameField.setPlaceholder("name");
            nameField.addClassName("subjectName");
            nameField.getElement().setAttribute("subjectName", "name");

            descriptionField.setLabel("Description");
            descriptionField.setPlaceholder("description");
            descriptionField.addClassName("subjectDescription");
            descriptionField.getElement().setAttribute("subjectDescription", "description");

            ectsField.setLabel("ECTS");
            ectsField.setPlaceholder("ects");
            ectsField.addClassName("subjectEcts");
            ectsField.getElement().setAttribute("subjectEcts", "ects");

            VerticalLayout vertical= new VerticalLayout(text, nameField, descriptionField, ectsField, button);
            vertical.addClassName("vertical");

    }
}
