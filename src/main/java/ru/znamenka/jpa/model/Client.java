package ru.znamenka.jpa.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.sql.Date;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity(name = "JF_clients")
public class Client implements BaseModel<Long> {

    @Id
    @Column(name = "client_id")
    @Getter @Setter
    private Long id;

    @Column(name = "surname")
    @Getter @Setter
    private String surname;

    @Column(name = "phone")
    @Getter @Setter
    private Integer phone;

    @Getter @Setter
    @Column(name = "email")
    private String email;

    @Getter @Setter
    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "name")
    @Getter @Setter
    private String name;

    @OneToMany(mappedBy = "client", fetch = LAZY)
    @Getter @Setter
    private List<Training> trainings;

    @OneToMany(mappedBy = "client", fetch = LAZY)
    @Getter @Setter
    private List<Purchase> purchases;

}
