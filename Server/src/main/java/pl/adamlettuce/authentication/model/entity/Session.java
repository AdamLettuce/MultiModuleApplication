package pl.adamlettuce.authentication.model.entity;


import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import pl.adamlettuce.authentication.model.domain.SessionToken;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "session")
public class Session {

    @Id
    @Column(name = "session_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "session_id")
    @SequenceGenerator(
            name = "session_id",
            sequenceName = "session_id",
            allocationSize = 1
    )
    private Long sessionId;

    @Length(max=50)
    private String username = "";

    @Length(max=65)
    private String token = "";

    @Column(name = "creation_date")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss.S")
    private Date creationDate;

    @Column(name = "active")
    private boolean active = true;

    private Session() {
    }

    public static Session newInstance(User user, Date creationDate, SessionToken sessionToken) {
        Session result = new Session();
        result.creationDate = creationDate;
        result.username = user.getUsername();
        result.token = sessionToken.getValue();
        return result;
    }

    public Long getSessionId() {
        return sessionId;
    }

    private void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    private void setCreationDate(Date validTo) {
        this.creationDate = creationDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
