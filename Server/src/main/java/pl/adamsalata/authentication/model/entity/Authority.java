package pl.adamsalata.authentication.model.entity;


import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authority")
public class Authority {

    @Id
    @Length(max=50)
    private String username;

    @Column(name = "authority")
    @Length(max=50)
    private String authority;

    public String getAuthority() {
        return authority;
    }

    private void setAuthority(String authority) {
        this.authority = authority;
    }
}
