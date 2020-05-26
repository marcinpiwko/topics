package com.siwz.app.api.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JwtFilter extends BasicAuthenticationFilter {

    public JwtFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        UsernamePasswordAuthenticationToken authResult = getAuthenticationByToken(header);
        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationByToken(String header) throws ServletException {
        if (header == null || !header.startsWith("Bearer ")) {
            throw new ServletException("Invalid token");
        }
        Jws<Claims> claims = Jwts.parser().setSigningKey("ICf*U^$v99c69'/*(8g".getBytes()).parseClaimsJws(header.substring(7));
        String username = claims.getBody().get("subject").toString();
        String role = claims.getBody().get("role").toString();
        return new UsernamePasswordAuthenticationToken(username, null, Collections.singletonList(new SimpleGrantedAuthority(role)));
    }
}
