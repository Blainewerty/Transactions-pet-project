package ru.milov.transactions.service.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "password")
    private String password;

    @Column(name = "total_balance")
    private BigDecimal totalBalance;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "bills",
//            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id")
//    )
//    private List<UserBill> billList;

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", totalBalance=" + totalBalance +
                '}';
    }
}
