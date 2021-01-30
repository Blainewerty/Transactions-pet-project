package ru.milov.transactions.service.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "bills")
public class UserBill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bill_id;

    @Column(name = "user_id")
    private Integer user_id;

//    @ManyToOne
//    @JoinColumn
//    private UserDto userDto;

    @Column(name = "name")
    private String name;

    @Column(name = "balance")
    private BigDecimal balance;


    @Override
    public String toString() {
        return name + '\t' + balance ;
    }
}
