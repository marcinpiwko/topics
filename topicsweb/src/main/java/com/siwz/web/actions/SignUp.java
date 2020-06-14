package com.siwz.web.actions;
import com.siwz.web.model.User;
import com.siwz.web.model.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;



@StyleSheet("/css/signup.css")
@PageTitle("SignUp")
@Route("signup")
public class SignUp extends VerticalLayout {

    private UserService service;
    private BeanValidationBinder<User> binder;
    private boolean enablePasswordValidation;
    private Button button = new Button("Login");
    private TextField nameField = new TextField();
    private EmailField emailField = new EmailField("Email");
    private PasswordField passwordField = new PasswordField();
    private PasswordField repasswordField = new PasswordField();
    private Button register = new Button("Register");


    public SignUp(@Autowired UserService service){

        this.service = service;

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

        binder = new BeanValidationBinder<User>(User.class);
        binder.forField(nameField).asRequired().bind("name");
        binder.forField(emailField).asRequired().bind("email");
        binder.forField(passwordField).asRequired().withValidator(this::passwordValidator).bind("password");

        repasswordField.addValueChangeListener(e -> {
            enablePasswordValidation = true;
            binder.validate();
        });
        //binder.setStatusLabel(errorMessage);

        register.addClickListener(e -> {
            try{
                User detailsBean = new User("", "", "");

                binder.writeBean(detailsBean);

                //service.store(detailsBean);
                showSuccess(detailsBean);
                System.out.println(detailsBean.getName() + detailsBean.getEmail() + detailsBean.getPassword());
            } catch (ValidationException e1) {

            }
        });
    }

    private void showSuccess(User detailsBean) {
        Notification notification = Notification.show("Zapisano");
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
    }

    private ValidationResult passwordValidator(String pass1, ValueContext ctx) {
        if (pass1 == null || pass1.length() <8 ){
            return ValidationResult.error("Haslo powinno sie skladac z min 8 znaków");
        }
        if (!enablePasswordValidation) {
            enablePasswordValidation = true;
            return ValidationResult.ok();
        }
        String pass2 = repasswordField.getValue();

        if(pass1 != null && pass1.equals(pass2)) {
            return ValidationResult.ok();
        }
        return ValidationResult.error("Hasla nie są takie same");
    }
}
