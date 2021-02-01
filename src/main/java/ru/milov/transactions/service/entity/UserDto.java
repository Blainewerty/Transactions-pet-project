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
@Table(name = "users")
public class UserDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    private String email;

    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;


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
                "id=" + user_id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", totalBalance=" + totalBalance +
                '}';
    }
}
