package pl.adamlettuce.employee.model.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @Column(name = "private_person_id")
    private Long privatePersonId;

    @Column(name = "hired_from")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date hiredFrom;

    @Column(name = "hired_to")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date hiredTo;

    @Column(name = "is_active")
    private boolean active;

    public Long getPrivatePersonId() {
        return privatePersonId;
    }

    public void setPrivatePersonId(Long privatePersonId) {
        this.privatePersonId = privatePersonId;
    }

    public Date getHiredFrom() {
        return hiredFrom;
    }

    public void setHiredFrom(Date hiredFrom) {
        this.hiredFrom = hiredFrom;
    }

    public Date getHiredTo() {
        return hiredTo;
    }

    public void setHiredTo(Date hiredTo) {
        this.hiredTo = hiredTo;
    }

    public void setHireDates(Date hiredFrom, Date hiredTo) {
        setHiredFrom(hiredFrom);
        setHiredTo(hiredTo);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
