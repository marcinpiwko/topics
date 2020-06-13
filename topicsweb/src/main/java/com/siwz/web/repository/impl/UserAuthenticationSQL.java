package com.siwz.web.repository.impl;

import com.siwz.web.model.User;
import com.siwz.web.repository.UserAuthentication;
import com.siwz.web.repository.exceptions.EmptyFieldException;
import com.siwz.web.repository.exceptions.NonUniqueNameException;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserAuthenticationSQL implements UserAuthentication {
    public static final String AUTHENTICATED_NAME = "authenticatedName";

    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserAuthenticationSQL(JdbcTemplate jdbcTemplate,
                                 PasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean checkAuthentication(User userRequest) {
        boolean result = false;

        List<User> users = jdbcTemplate.query(
                "SELECT * FROM user WHERE name = ?",
                new String[]{userRequest.getName()},
                (rs, rowNum) -> new User(rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"))
        );
        if(users.size() != 0){
            User user = users.get(0);

            if(passwordEncoder.matches(userRequest.getPassword(), user.getPassword())){
                result = true;
                VaadinSession.getCurrent().setAttribute(AUTHENTICATED_NAME, user.getName());
            }
        }
        return result;
    }

    @Override
    public void addNewUser(User userRequest) throws Exception {
        String name = userRequest.getName();
        String email = userRequest.getEmail();
        String password = userRequest.getPassword();

        if(name.equals("") || email.equals("") || password.equals("")){
            throw new EmptyFieldException();
        }
        try{
            userRequest.setPassword(passwordEncoder.encode(password));
            jdbcTemplate.update(
                    "INSERT INTO user(name, email, password) VALUES(?, ?, ?)",
                    name, email, userRequest.getPassword()
            );
        } catch (DuplicateKeyException e){
            throw new NonUniqueNameException();
        }
    }

    @Override
    public void signOut() {
        VaadinSession.getCurrent().close();
        //Page.getCurrent().reload();
    }
}
