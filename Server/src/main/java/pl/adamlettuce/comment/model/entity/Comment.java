package pl.adamlettuce.comment.model.entity;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_sequence")
    @SequenceGenerator(
            name = "comment_sequence",
            sequenceName = "comment_sequence",
            allocationSize = 1
    )
    private long comment_id;

    @Column(name = "username")
    private String username;

    @Column(name = "text")
    @Length(max = 200)
    private String text;

    @Column(name = "active")
    private boolean active = true;

    @Column(name = "created_date")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss.S")
    private Date createdDate;

    public long getId() {
        return comment_id;
    }

    private void setId(long id) {
        this.comment_id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
