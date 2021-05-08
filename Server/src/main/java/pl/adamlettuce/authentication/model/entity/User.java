package pl.adamlettuce.authentication.model.entity;


import org.hibernate.validator.constraints.Length;


import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "application_user")
public class User {

    @Id
    @Length(max=50)
    private String username;

    @Length(max=61)
    private String password;

    @OneToMany(mappedBy = "username")
    private Collection<Authority> authorities;

    private boolean enabled;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Authority> getAuthorities() {
        if(authorities == null) {
            return new HashSet<>();
        }
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

}
