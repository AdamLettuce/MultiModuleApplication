package pl.adamsalata.authentication.security;

import pl.adamsalata.authentication.model.entity.Authority;
import pl.adamsalata.authentication.model.entity.User;

import java.util.Collection;

public class UserDetailsImpl {

    private User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    public Collection<Authority> getAuthorities() {
        return user.getAuthorities();
    }

    public String getPassword() {
        return user.getPassword();
    }

    public String getUsername() {
        return user.getUsername();
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

}
