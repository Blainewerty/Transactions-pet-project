package ru.milov.transactions.service.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@ToString
@Accessors(chain = true)
@Table(name = "users")
public class UserDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;


    private BigDecimal totalBalance;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "bills",
//            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id")
//    )
//    private List<UserBill> billList;


}
