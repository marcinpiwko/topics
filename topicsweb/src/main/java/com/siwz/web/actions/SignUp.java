package com.siwz.web.actions;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.NoTheme;


import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
@StyleSheet("/css/signup.css")
@Route("signup")

public class SignUp extends VerticalLayout{
    public SignUp(){
        //menubar
        Button button = new Button("Login");
        button.addClassName("login");

        button.addClickListener(clickEvent -> {
            LoginForm component = new LoginForm();

            add(component);
            component.addLoginListener(e -> {
                boolean isAuthenticated = true; //authenticate(e);
                if (isAuthenticated) {
                    //navigateToMainPage();
                } else {
                    component.setError(true);
                }
            });
        });
        //content

        Icon icon = new Icon(VaadinIcon.ACADEMY_CAP);
        icon.addClassName("cap");

        Label text = new Label("Create an Account");
        text.addClassName("description");

        TextField name = new TextField();
        name.setLabel("Name");
        name.setPlaceholder("John");
        name.addClassName("name");

        EmailField emailField = new EmailField("Email");
        emailField.addClassName("email");
        emailField.setClearButtonVisible(true);
        emailField.setPlaceholder("domin@gmail.com");
        emailField.setErrorMessage("Please enter a valid email address");

        PasswordField passwordField = new PasswordField();
        passwordField.addClassName("password");
        passwordField.setLabel("Password");
        passwordField.setPlaceholder("Enter password");
        passwordField.setValue("secret1");

        PasswordField repasswordField = new PasswordField();
        repasswordField.addClassName("password");
        repasswordField.setLabel("Confirm Password");
        repasswordField.setPlaceholder("Enter password");
        repasswordField.setValue("secret1");
        Button register = new Button("Register");
        register.addClassName("register");
        Label label = new Label();
        /*register.addClickListener(clickEvent -> {
           try{
               label.setText(null);
           }catch (IOException e){

           }
        });*/


        VerticalLayout vertical= new VerticalLayout(text, name, emailField, passwordField, repasswordField, register, label);
        vertical.addClassName("vertical");
        HorizontalLayout horizontal = new HorizontalLayout(icon, vertical);
        horizontal.addClassName("horizontal");

        add(button, horizontal);
    }
}
