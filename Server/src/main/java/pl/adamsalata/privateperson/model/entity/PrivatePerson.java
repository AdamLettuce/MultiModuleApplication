package pl.adamsalata.privateperson.model.entity;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.springframework.format.annotation.DateTimeFormat;
import pl.adamsalata.address.model.entity.Address;
import pl.adamsalata.employee.model.entity.Employee;
import pl.adamsalata.privateperson.model.enumeration.Sex;

import javax.persistence.*;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "private_person")
public class PrivatePerson {

    @Id
    @Column(name = "private_person_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "private_person_sequence")
    @SequenceGenerator(
            name = "private_person_sequence",
            sequenceName = "private_person_sequence",
            allocationSize = 1
    )
    private Long privatePersonId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Employee employee;

    @Column(name = "email")
    private String email;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "private_person_address"
            , joinColumns = {@JoinColumn(name = "private_person_id")}
            , inverseJoinColumns = {@JoinColumn(name = "address_id")
    })
    private Set<Address> addresses = new HashSet<>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getId() {
        return privatePersonId;
    }

    public void setPrivatePersonId() {
        this.privatePersonId = privatePersonId;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void addAddress(Address address) {
        this.addresses.add(address);
    }

    public Set<Address> getAddresses() {
        return Collections.unmodifiableSet(addresses);
    }

    private void setAddresses(Set<Address> addresses) {
        for (Address address : addresses) {
            addAddress(address);
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
