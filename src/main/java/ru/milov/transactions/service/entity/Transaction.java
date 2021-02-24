package ru.milov.transactions.service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn
    private User user;

    @ManyToOne
    @JsonIgnore
    @JoinColumn
    private Bill bill;

    private Timestamp date;

    @Column(name = "name_of_transaction")
    private String nameOfTransaction;

    private BigDecimal valueOfTransaction;
}
