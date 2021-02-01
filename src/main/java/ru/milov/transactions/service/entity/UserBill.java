package ru.milov.transactions.service.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "bills")
public class UserBill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bill_id;

    private Long user_id;

//    @ManyToOne
//    @JoinColumn
//    private UserDto userDto;

    private String name;

    private BigDecimal balance;


    @Override
    public String toString() {
        return name + '\t' + balance ;
    }
}
