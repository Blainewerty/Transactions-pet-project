package ru.milov.transactions.service.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;

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
    private Long user_id;
    private Long bill_id;
    private String date;
    private String nameOfTransaction;
    BigDecimal valueOfTransaction;

    @Override
    public String toString() {
        return  date + '\t' + nameOfTransaction + '\t' + valueOfTransaction + '\t';
    }
}
