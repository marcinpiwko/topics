package com.siwz.web.actions;

import com.siwz.web.model.User;
import com.siwz.web.repository.UserAuthentication;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.ui.Notification;

@StyleSheet("/css/signup.css")
@Route("signup")
@PageTitle("SignUp")

public class SignUp extends VerticalLayout {

    private UserAuthentication userAuthentication;
    private Binder<User> userBinder = new Binder<>();
    private User user = new User("", "", "");
    private Button button = new Button("Login");
    private TextField nameField = new TextField();
    private EmailField emailField = new EmailField("Email");
    private PasswordField passwordField = new PasswordField();
    private PasswordField repasswordField = new PasswordField();
    private Button register = new Button("Register", e -> signUp(user));


    public SignUp(){
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

        nameField.setLabel("Name");
        nameField.setPlaceholder("John");
        nameField.addClassName("name");
        nameField.getElement().setAttribute("name", "username");

        emailField.addClassName("email");
        emailField.setClearButtonVisible(true);
        emailField.setPlaceholder("domin@gmail.com");
        emailField.setErrorMessage("Please enter a valid email address");

        passwordField.addClassName("password");
        passwordField.setLabel("Password");
        passwordField.setPlaceholder("Enter password");
        passwordField.setValue("secret1");
        passwordField.getElement().setAttribute("name", "password");

        repasswordField.addClassName("password");
        repasswordField.setLabel("Confirm Password");
        repasswordField.setPlaceholder("Enter password");
        repasswordField.setValue("secret1");
        register.addClassName("register");
        Label label = new Label();


        VerticalLayout vertical= new VerticalLayout(text, nameField, emailField, passwordField, repasswordField, register, label);
        vertical.addClassName("vertical");
        HorizontalLayout horizontal = new HorizontalLayout(icon, vertical);
        horizontal.addClassName("horizontal");

        add(button, horizontal);
    }
    private void signUp(User userRequest){
        try{
            userAuthentication.addNewUser(userRequest);
        } catch (Exception e){
            Notification.show("Bledna rejestracja " + e.getMessage(),
                    Notification.Type.ERROR_MESSAGE);
        }
    }
}
