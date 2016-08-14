package ru.znamenka.jpa.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;

@Entity(name = "JF_user")
public class User implements UserDetails {

    @Id
    @Column(name = "username", nullable = false, unique = true)
    @Getter @Setter
    private String username;

    @Column(name = "password", nullable = false)
    @Getter @Setter
    private String password;

    @Transient
    @Getter @Setter
    private List<GrantedAuthority> authorities;


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
