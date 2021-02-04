package ru.milov.transactions.service.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transaction_id;

    @ManyToOne
    @JoinColumn
    private User user;

    @ManyToOne
    @JoinColumn
    private Bill bill;

    private String date;

    private String nameOfTransaction;

    private BigDecimal valueOfTransaction;
}
