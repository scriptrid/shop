package ru.scriptrid.common.security;

import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;


public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final int id;

    private final String username;

    private final String email;

    private final boolean isAdmin;

    private final List<GrantedAuthority> authorities;

    public JwtAuthenticationToken(Claims claims) {

        super(null);
        this.id = Integer.parseInt(claims.getSubject());
        this.username = claims.get("username", String.class);
        this.email = claims.get("email", String.class);
        this.isAdmin = claims.get("isAdmin", Boolean.class);

        if (isAdmin) {
            this.authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"), new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            this.authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }

    }


    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return username;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        throw new IllegalStateException();
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
