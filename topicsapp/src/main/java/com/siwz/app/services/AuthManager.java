package com.siwz.app.services;

import com.siwz.app.persistence.dto.User;
import com.siwz.app.persistence.repositories.UserRepository;
import com.siwz.app.services.interfaces.AuthService;
import com.siwz.app.utils.errors.ApplicationException;
import com.siwz.app.utils.errors.DAOError;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthManager implements AuthService {

    private final UserRepository userRepository;

    public String getUserToken(String username, String password) throws ApplicationException {
        User user = userRepository.findByEmail(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new ApplicationException(DAOError.DAO_INVALID_CREDENTIALS);
        }
        return generateJWT(user);
    }

    private String generateJWT(User user) {
        long currentTimeMillis = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("roles", "ROLE_" + user.getRole())
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(currentTimeMillis + 1000000))
                .signWith(SignatureAlgorithm.HS512, user.getPassword())
                .compact();
    }
}
