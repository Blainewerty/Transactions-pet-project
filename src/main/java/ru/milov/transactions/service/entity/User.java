package ru.milov.transactions.service.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
@Accessors(chain = true)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "bills", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    private List<Bill> bill;


}
