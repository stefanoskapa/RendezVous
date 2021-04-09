package com.rendezvous.security;

import com.rendezvous.entity.Role;
import com.rendezvous.entity.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/* 
Spring Security is going to take the values that are returned
by UserDetails and will use them for authentication
 */
public class MyUserDetails implements UserDetails {

    private String username;
    private String password;
    private Collection<GrantedAuthority> authorities;

    public MyUserDetails(User user) {
        this.username = user.getEmail();
        this.password = user.getPassword();
        List<GrantedAuthority> ga = new ArrayList<>();
        for (Role a : user.getRoleList()) {
            ga.add(new SimpleGrantedAuthority(a.getRole()));
        }
        this.authorities = ga;
    }

    public MyUserDetails() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
